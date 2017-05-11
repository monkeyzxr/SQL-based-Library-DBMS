package zxr.library;

public class SearchModel {
	String searchmodel_type;
	String model_comp;
	String details;
	String model;
	
	//define constructor
	public SearchModel(String st, String mc, String det, String mod){
        this.searchmodel_type = st;
        this.model_comp = mc;
        this.details = det;
        this.model = mod;       		
	}

	public boolean verfyDetails(){
		return verfyDetails(details);
	}
	
	static public boolean verfyDetails(String det){
		if((det==null)||(det.isEmpty())) return false;
		return true;
	}
	
	public String declareLocation(){
		String location = "";
		if(verfyDetails()){
			details.replace("'", "''");
			location = " " + searchmodel_type + " " +model_comp + " '"+details+"' ";
		}
		return location;
	}
	

	public static String createLocation(SearchModel models[]){
		String location = models[0].declareLocation();
		String model = models[0].model;
		for(int i = 1; i < models.length; i++){
			if((models[i].verfyDetails())&&(SearchModel.verfyDetails(location))){			
					location = location + model + models[i].declareLocation();
					model = models[i].model;
				}
			else{
				location = models[i].declareLocation();
				model = models[i].model;
				}
			}	
		return location;
	}
	
}
