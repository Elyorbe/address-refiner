package me.elyor.addressrefiner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@Slf4j
@ShellComponent
public class RefinerCommand {

    private RoadNameExtractor roadNameExtractor;

    public RefinerCommand(RoadNameExtractor roadNameExtractor) {
        this.roadNameExtractor = roadNameExtractor;
    }

    @ShellMethod("Extract road name from address")
    public void refine(String value) {
        List<String> roadNames = roadNameExtractor.extractRoadNames(value);
        System.out.println(roadNames);
    }
}
