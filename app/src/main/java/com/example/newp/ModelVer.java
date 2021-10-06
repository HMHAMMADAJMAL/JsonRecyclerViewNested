package com.example.newp;



public class ModelVer {
    private String Title,Target,Raised,Desc;

    public ModelVer(String Title, String Target, String Raised, String Desc)
    {
     this.Title=Title;
     this.Target=Target;
     this.Raised=Raised;
     this.Desc=Desc;
    }
    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        Title = title;
    }
    public String getTarget()
    {
        return Target;
    }

    public void setTarget(String target)
    {
        Target = target;
    }
    public String getRaised()
    {
        return Raised;
    }

    public void setRaised(String raised)
    {
        Raised = raised;
    }
    public String getDesc()
    {
        return Desc;
    }

    public void setDesc(String desc)
    {
        Desc = Desc;
    }

}