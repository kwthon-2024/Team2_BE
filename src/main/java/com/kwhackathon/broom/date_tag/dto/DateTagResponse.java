package com.kwhackathon.broom.date_tag.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.kwhackathon.broom.date_tag.entity.DateTag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DateTagResponse {
    @Getter
    @NoArgsConstructor
    public static class TrainingDateList {
        private List<TrainingDateElement> dates;

        public TrainingDateList(List<DateTag> tags) {
            this.dates = tags.stream()
                    .map((tag) -> new TrainingDateElement(tag))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class TrainingDateElement {
        private Long id;
        private String trainingDate;

        public TrainingDateElement(DateTag dateTag) {
            this.id = dateTag.getDateTagId();
            this.trainingDate = dateTag.getTrainingDate().format(DateTimeFormatter.ofPattern(
                                    "yyyy.MM.dd"));
        }
    }
}
