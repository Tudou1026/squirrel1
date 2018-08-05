package com.ldu.controller;

import com.ldu.service.CatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 */
@Controller
public class CatelogController {
    @Autowired
    private CatelogService catelogService;
}
