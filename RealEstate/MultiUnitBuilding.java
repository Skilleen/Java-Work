package assin6;
/**
 * This is an abstract class that is the parent of both office buildings and apartment buildings.
 *It contains methods used by both office buildings and apartment buildings
 */
public abstract class MultiUnitBuilding extends Building{
	protected int units;
	protected boolean elevator;
	//Adding an elevator to either office buildings or apartment buildings
	public void addElevator() throws BuildingException{
		if(elevator)
			throw new BuildingException("building already has an existing elevator");
		this.elevator=true;
	}
	//Adding units onto either office buildings or apartment buildings
	public void extension(int newUnits){
		this.units+=newUnits;
	}

}
