package com.example.tasklist;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
public class HomeRestController {

    record TaskItem(String id, String task, String deadline, boolean done){}
    private List<TaskItem> taskItems = new ArrayList<>();
    
    @RequestMapping("/resthello")
    String Hello(){
        return """
                Hello.
                It works!
                現在時刻は%sです。
                """.formatted(LocalDateTime.now());
    }


    @GetMapping("/restadd")
    String addItem(@RequestParam("task") String task,
                  @RequestParam("deadline") String deadline){

        String id = UUID.randomUUID().toString().substring(0,8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);
                    
        return "タスクを追加しました。";
    }

    @GetMapping("/restlist")    
    String listItems(){
        String result = taskItems.stream()
                        .map(TaskItem :: toString)
                        .collect(Collectors.joining(","));
        return result;
    }
}
