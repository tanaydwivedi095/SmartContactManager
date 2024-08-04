package com.scm.SmartContactManager.forms;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpForm {

    private String name;
    private String about;
    private String message;

}
