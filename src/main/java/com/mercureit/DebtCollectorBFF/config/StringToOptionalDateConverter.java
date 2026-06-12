//package com.mercureit.DebtCollectorBFF.config;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Optional;
//
//@Component
//public class StringToOptionalDateConverter implements Converter<String, Optional<Date>> {
//
//    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
//
//    @Override
//    public Optional<Date> convert(String source) {
//        if (source == null || source.isEmpty()) {
//            return Optional.empty();
//        }
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
//            return Optional.of(formatter.parse(source));
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format. Please use " + DATE_FORMAT);
//        }
//    }
//}
