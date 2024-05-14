package com.example.data_analysis_microservice.service;

import com.example.data_analysis_microservice.dto.ModelDataDTO;
import com.example.data_analysis_microservice.dto.RawInputDTO;
import com.example.data_analysis_microservice.mapper.PredictionMapper;
import com.sun.tools.javac.Main;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.pmml4s.model.Model;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.Map.entry;

@Service
@AllArgsConstructor
public class PredictionService {
    private final Model model = Model.fromFile("C:/Users/Emma/Desktop/HAPrediction/model.pmml");
    private final PredictionMapper predictionMapper;

    public String getPrediction(RawInputDTO rawInputDTO){
        ModelDataDTO modelDataDTO = predictionMapper.rawToModelData(rawInputDTO);
        System.out.println("Model expects inputs: " + Arrays.toString(model.inputNames()));

        Map<String, Double> values = new LinkedHashMap<>();
        values.put("age", toDouble(modelDataDTO.getAge()));
        values.put("sex", toDouble(modelDataDTO.getGender()));
        values.put("chest_pain", toDouble(modelDataDTO.getCp()));
        values.put("resting_blood_pressure", toDouble(modelDataDTO.getTrestbps()));
        values.put("cholesterol", toDouble(modelDataDTO.getChol()));
        values.put("fasting_blood_sugar", toDouble(modelDataDTO.getFbs()));
        values.put("rest_ecg", toDouble(modelDataDTO.getRestecg()));
        values.put("max_heart_rate_achieved", toDouble(modelDataDTO.getThalach()));
        values.put("exercise_induced_angina", toDouble(modelDataDTO.getExang()));
        values.put("st_depression", toDouble(modelDataDTO.getOldpeak()));
        values.put("st_slope", toDouble(modelDataDTO.getSlope()));
        values.put("ca", toDouble(modelDataDTO.getCa()));
        values.put("thalassemia", toDouble(modelDataDTO.getThal()));


        values.forEach((key, value) -> System.out.println(key + ": " + value + " (" + value.getClass().getSimpleName() + ")"));

        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();
        Object[] result = model.predict(valuesMap);
        System.out.println(Arrays.toString(result));
        return result[0].toString();

    }
    private Double toDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            throw new IllegalArgumentException("Non-numeric value encountered: " + value);
        }
    }
}
