package Lab.Things;

public class Details {

    protected int degree_of_breakage, quality;
    protected boolean isSkillNeed;
    protected String name;

    protected String skillNeed = "механик";
    public String getSkillNeed(){
        return skillNeed;
    }

    public int getQuality(){
        return quality;
    }
    public void setQuality(int power){
        quality += power;
        if(quality > 100){
            quality = 100;
        }
    }
    public int getDegree_of_breakage(){
        return degree_of_breakage;
    }
    public String getName(){
        return name;
    }

    public boolean getIsSkiilNeed(){
        return isSkillNeed;
    }
}
