package com.airtribe.trustdesk.service;
import org.springframework.stereotype.Service;
import com.airtribe.trustdesk.entity.*;
import com.airtribe.trustdesk.repository.*;
import java.util.*;

@Service

public class KnowledgeService {
    private final KnowledgeRepository repo;
    public KnowledgeService(KnowledgeRepository r){repo=r;
    }
    public List<KnowledgeDocument> search(String q){
        Set<String> terms=new HashSet<>(Arrays.asList(q.toLowerCase().replaceAll("[^a-z0-9 ]"," ").split("\\s+")));
        return repo.findAll().stream().filter(d->d.trusted).map(d->Map.entry(d, score(d,terms))).filter(e->e.getValue()>0).sorted((a,b)->Integer.compare(b.getValue(),a.getValue())).limit(5).map(Map.Entry::getKey).toList();
    }
    private int score(KnowledgeDocument d,Set<String> t){
        String c=(d.title+" "+d.content).toLowerCase();
        int s=0;
        for(String x:t)
            if(x.length()>2&&c.contains(x))s++;
        return s;
    }
}
