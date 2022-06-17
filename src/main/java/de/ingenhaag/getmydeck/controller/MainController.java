package de.ingenhaag.getmydeck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
  @RequestMapping(value = {
      "/{path:^(?!api)[^\\.]*}",
      "/**/{path:^(?!api).*}/{path:[^\\.]*}"}
  )
  public String index(Model model) {
    return "index";
  }

}