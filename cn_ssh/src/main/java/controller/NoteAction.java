package controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class NoteAction extends JsonAction {

    public String toIndex(){
        return SUCCESS;
    }
}
