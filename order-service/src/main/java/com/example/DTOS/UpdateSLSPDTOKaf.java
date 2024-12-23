package com.example.DTOS;

import com.example.enumss.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSLSPDTOKaf {
    List<UpdateSLSPDTO> updates;
    Operation operation;
}
