import java.util.*;

public class Participant implements Comparable<Participant> {
    
    private HashMap<String, Integer> languages;
    
    private List<String> languageNames;
    
    private String username;
    
    private String password;
    
    private String college="not set";
    
    private List<String> major;
    
    public Participant()
    {
        this.languages=new HashMap<>();
        languageNames=new ArrayList<>();
        major=new ArrayList<>();
    }


    public void setUserName(String username)
    {
        this.username=username;
    }

    
    public String getUsername()
    {
        return username;
    }
    
    public void setPassword(String password)
    {
        this.password=password;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public HashMap<String, Integer> getLanguagesMap()
    {
        return languages;
    }
    
    public List<String> getLanguageNames()
    {
        return languageNames;
    }


    @Override
    public int compareTo(Participant o) {
        int score=0;
        if(this.college.equalsIgnoreCase(o.getCollege()))
        {
            score+=10;
        }
        for(int i=0;i<major.size();i++)
        {
            if(o.getMajor().contains(major.get(i)))
            {
                score+=10;
            }
        }
        for(int i=0;i<languageNames.size();i++)
        {
            if(o.getLanguageNames().contains(languageNames.get(i)))
                    {
                score+=5;
                if(languages.get(languageNames.get(i))-o.getLanguagesMap().get(languageNames.get(i))<2)
                {
                    score+=30;
                }
                    
        }
        }
        return score;

    }
    
    public String toString()
    {
        
        if(college==null&&major.size()==0&&languages.size()==0)
        {
            return "You do not have information in your profile.";
        }
        String temp= "College: " + college + "\nMajor: " + major;
        if(languages.size()!=0)
        {
        for(int i=0;i<languageNames.size();i++)
        {
            temp+="\n"+languageNames.get(i)+ ": " + languages.get(languageNames.get(i));
        }
        }
        else
        {
            temp+="\nIs an eager beginner";
        }
    
        return temp;
    }
    
    public void addMajor(String major)
    {
        this.major.add(major);
    }
    
    public void setCollege(String college)
    {
        this.college=college;
    }


    public List<String> getMajor() {
        return major;
    }
    
    public String getCollege()
    {
        return college;
    }
    
    
    
}

