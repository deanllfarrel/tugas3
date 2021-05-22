package tugas2;

public class Credit extends Payment {
	protected int installment, maxInstallment;
	public Credit(Item item, int maxInstallment){
	super (item);
	this.maxInstallment = maxInstallment;
	this.installment = maxInstallment - 1;
	}

	@Override
	public int pay(){
		if(isPaidOff){
			return 0;
		}
		return this.item.getPrice() / maxInstallment;
	}

	@Override
	public int getRemainingAmount (){
		if(isPaidOff){
			return 0;
		}
		return this.item.getPrice() * installment / maxInstallment;
	}
	
	@Override
	public String getClassName(){
		return "CREDIT";
	}

	public void payItem (){
		installment --;
		if (installment == 0){
			isPaidOff = true;
		}
	}
}

