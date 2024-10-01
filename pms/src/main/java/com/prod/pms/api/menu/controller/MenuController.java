package com.prod.pms.api.menu.controller;

import com.prod.pms.constants.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_URL + "/menu")
public class MenuController {
}
