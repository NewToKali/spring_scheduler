package com.example.scheduler.rest;

import com.example.scheduler.config.CmdProps;
import com.example.scheduler.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scheduler.service.Command;

import net.javacrumbs.shedlock.core.SchedulerLock;

@RequestMapping("api")
@RestController
public class ScheduledTasks {
    
    @Autowired
    Command command;
    @Autowired
    CmdProps cmdProps;

    Response response = new Response();

    // test
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("import")
    @Scheduled(cron = "1 * * * * ?", zone = "America/New_York")
    // @Scheduled(cron = "${scheduler.import.cron}", zone = "America/New_York")
    @SchedulerLock(name = "import", lockAtLeastForString = "PT10S", lockAtMostForString = "PT20S")
    public ResponseEntity<Response> importTask() {
        System.out.println(cmdProps.getImportMode3());

        response.setStatusMsg("command ran with the below output: ");
        response.setCmdOutput(command.exec(cmdProps.getImportMode3()));
        response.setStatusCode("200");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

//    @GetMapping("export")
//    @Scheduled(cron = "* * * * * ?", zone = "America/New_York")
//    // @Scheduled(cron = "${scheduler.export.cron}", zone = "America/New_York")
//    @SchedulerLock(name = "export", lockAtLeastForString = "PT10S", lockAtMostForString = "PT20S")
//    public void exportTask() {
//        System.out.println(cmdProps.getExport_mode3());
//        command.exec(cmdProps.getExport_mode3());
//    }


}
