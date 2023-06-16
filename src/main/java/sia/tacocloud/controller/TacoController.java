package sia.tacocloud.controller;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sia.tacocloud.models.Taco;
import sia.tacocloud.repositories.TacoRepository;

@RestController
@RequestMapping(path = "api/tacos",produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    private TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo){
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        Pageable   pageRequest = PageRequest.of(0, 12, Sort.by("createAt").descending());
        return tacoRepo.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> optTaco =  tacoRepo.findById(id);
        if(optTaco.isPresent()){
            return ResponseEntity.ok(optTaco.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
       return tacoRepo.save(taco);
    }

}