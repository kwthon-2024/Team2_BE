package com.kwhackathon.broom.bus.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservations {
    private List<ReservationInfoElement> result;
}
