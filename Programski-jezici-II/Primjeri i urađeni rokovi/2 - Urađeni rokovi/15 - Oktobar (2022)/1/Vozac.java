public class Vozac extends Radnik {
	
	public Vozac() {
		super();
	}
	
	@Override
	public void run() {
		
		while (!this.isInterrupted()) {
			
			synchronized (Main.stopObject) {
				if (Main.stop) {
					try {
						Main.stopObject.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
			
			Predmet predmet = null;
			synchronized (Main.lockObject) {
				predmet = Main.skladiste.poll();
			}
			
			if (predmet != null) {
				if (this instanceof ViljuskarInterface) {
					if (predmet instanceof Kutija) {
						synchronized (Main.lockObject) {
							if (Main.random.nextInt(100) < predmet.vjerovatnocaOstecenja) {
								predmet.statusOstecenja = true;
							}
							Main.proizvodaTraka.add(predmet);
						}
						System.out.println(this + " je stavio " + predmet + " na proizvodnu traku");
						
						try {
							sleep(500);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				} else {
					synchronized (Main.lockObject) {
						if (Main.random.nextInt(100) < predmet.vjerovatnocaOstecenja) {
							predmet.statusOstecenja = true;
						}
						Main.proizvodaTraka.add(predmet);
					}
					System.out.println(this + " je stavio " + predmet + " na proizvodnu traku");
					
					try {
						sleep(500);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
		synchronized (Main.lockObject) {
			try {
				Main.lockObject.wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		while (!Thread.interrupted()) {
			synchronized (Main.lockObject) {
				if (Main.proizvodaTraka.isEmpty()) {
					try {
						Main.lockObject.wait();
					} catch (InterruptedException ex) {
						
					}
					
				} else {
					Predmet predmet = Main.proizvodaTraka.poll();
					Main.skladiste.add(predmet);
					System.out.println(this + " je vratio " + predmet + " u skladište");
				}
			}
		}
	}
}