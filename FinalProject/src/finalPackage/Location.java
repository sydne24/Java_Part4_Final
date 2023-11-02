package finalPackage;

public class Location
{
    private String locationDescription;
    
    public Location(String description) {
        this.locationDescription = description;
    }
    
    public void setDescription(String description) {
        this.locationDescription = description;
    }
    
    public String getDescription() {
        return locationDescription;
    }
    
    public class LocationObject
    {
        String objectDescription;
        String state;
        
        public LocationObject(String description) {
            this.objectDescription = description;
        }
        
        public void setDescription(String description) {
            this.objectDescription = description;
        }
        
        public String getDescription() {
            return objectDescription;
        }
        
        public void setState(String state) {
            this.state = state;
        }
        
        public String getState() {
            return state;
        }
    }

}