package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.PublishersDto;
import java.util.List;

public interface PublishersService {
    List<PublishersDto> findAll();
}
