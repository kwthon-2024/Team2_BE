package com.kwhackathon.broom.bus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Reservations {
    private List<ReservationInfoElement> result;
}
