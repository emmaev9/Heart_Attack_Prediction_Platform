package com.example.data_analysis_microservice.mapper;

import com.example.data_analysis_microservice.dto.ModelDataDTO;
import com.example.data_analysis_microservice.dto.RawInputDTO;
import org.springframework.stereotype.Component;

@Component
public class PredictionMapper {

    public ModelDataDTO rawToModelData(RawInputDTO rawInputDTO){
        System.out.println(rawInputDTO);
        ModelDataDTO modelDataDTO = new ModelDataDTO();
        modelDataDTO.setAge(rawInputDTO.getAge());
        modelDataDTO.setTrestbps(rawInputDTO.getTrestbps());
        modelDataDTO.setChol(rawInputDTO.getChol());
        modelDataDTO.setThalach(rawInputDTO.getThalach());
        modelDataDTO.setOldpeak(rawInputDTO.getOldpeak());
        modelDataDTO.setCa(rawInputDTO.getCa());

        if ((rawInputDTO.getGender().equals("Female"))) {
            modelDataDTO.setGender(0);
        } else {
            modelDataDTO.setGender(1);
        }

        switch (rawInputDTO.getCp()) {
            case "Typical Angina" -> modelDataDTO.setCp(0);
            case "Atypical Angina" -> modelDataDTO.setCp(1);
            case "Non-Anginal Pain" -> modelDataDTO.setCp(2);
            case "Asymptomatic" -> modelDataDTO.setCp(3);
        }

        if(rawInputDTO.getFbs() > 120){
            modelDataDTO.setFbs(1);
        }else{
            modelDataDTO.setFbs(0);
        }

        switch (rawInputDTO.getThal()){
            case "Normal" -> modelDataDTO.setThal(3);
            case "Fixed defect (no blood flow in some part of the heart)" -> modelDataDTO.setThal(6);
            case "Reversible defect (a blood flow is observed but it is not normal)" -> modelDataDTO.setThal(7);
        }

        switch (rawInputDTO.getSlope()){
            case "Up sloping" -> modelDataDTO.setSlope(1);
            case "Upsloping" -> modelDataDTO.setSlope(1);
            case "Flat" -> modelDataDTO.setSlope(2);
            case "Down sloping" -> modelDataDTO.setSlope(3);
            case "Downsloping" -> modelDataDTO.setSlope(3);
        }

        switch (rawInputDTO.getRestecg()){
            case "Normal" -> modelDataDTO.setRestecg(0);
            case "Having ST-T wave abnormality" -> modelDataDTO.setRestecg(1);
            case "Showing probable or definite left ventricular hypertrophy" -> modelDataDTO.setRestecg(2);
        }

        switch (rawInputDTO.getExang()){
            case "No" -> modelDataDTO.setExang(0);
            case "Yes" -> modelDataDTO.setExang(    1);
        }
        return modelDataDTO;
    }
}
