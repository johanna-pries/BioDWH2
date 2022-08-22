package de.unibi.agbi.biodwh2.interpro.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Interpro {
    @JacksonXmlProperty(isAttribute = true)
    public String id;
    @JacksonXmlProperty(isAttribute = true)
    public String type;
    @JacksonXmlProperty(localName = "short_name", isAttribute = true)
    public String shortName;
    @JacksonXmlProperty(localName = "protein_count", isAttribute = true)
    public Integer proteinCount;
    public String name;
    @JacksonXmlProperty(localName = "abstract")
    public String _abstract;
    @JacksonXmlProperty(localName = "pub_list")
    public List<Publication> pubList;
    @JacksonXmlProperty(localName = "member_list")
    public List<DBXref> memberList;
    @JacksonXmlProperty(localName = "class_list")
    public List<Classification> classList;
    @JacksonXmlProperty(localName = "external_doc_list")
    public List<DBXref> externalDocList;
    @JacksonXmlProperty(localName = "parent_list")
    public List<RelRef> parentList;
    @JacksonXmlProperty(localName = "child_list")
    public List<RelRef> childList;
    @JacksonXmlProperty(localName = "structure_db_links")
    public List<DBXref> structureDbLinks;
    @JacksonXmlProperty(localName = "taxonomy_distribution")
    public List<TaxonData> taxonomyDistribution;
    @JacksonXmlProperty(localName = "key_species")
    public List<TaxonData> keySpecies;
}
