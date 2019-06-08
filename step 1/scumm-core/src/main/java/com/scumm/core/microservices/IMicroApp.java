package com.scumm.core.microservices;

import org.modelmapper.ModelMapper;

public interface IMicroApp {
    static void main(String[] args) {}
    ModelMapper modelMapper();
    MicroSpringContainer container();
}
