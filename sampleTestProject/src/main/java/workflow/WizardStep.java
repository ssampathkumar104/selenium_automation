package workflow;

public final class WizardStep {
	
	private Class<? extends Step> step;
	  
	  public WizardStep(Class<? extends Step> step) {
	    this.step = step;
	  }
	  
	  public Class<? extends Step> getStep() {
	    return this.step;
	  }
	  
	  @Deprecated
	  public Class<? extends Step> getPage() {
	    return this.step;
	  }

}
