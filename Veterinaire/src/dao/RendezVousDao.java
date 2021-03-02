package dao;

import beans.RendezVous;

public interface RendezVousDao {
	void addRendezVous(RendezVous rendezVous);
	void updateRendezVous(RendezVous rendezVous);
	void deleteRendezVous(Long id);
}
