package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
@Entity
public class KnowledgeDocument {
    @Id

    public String docId;
    public String title;

    @Column(length=12000)

    public String content;
    public boolean trusted=true;
    public KnowledgeDocument(){}
    public KnowledgeDocument(String id,String t,String c,boolean tr){

        docId=id;
        title=t;
        content=c;
        trusted=tr;
    }
}
