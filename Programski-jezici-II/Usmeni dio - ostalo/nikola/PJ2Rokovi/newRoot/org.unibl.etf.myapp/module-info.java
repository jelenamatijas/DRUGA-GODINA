module org.unibl.etf.myapp {
	exports org.unibl.etf.myapp to org.unibl.etf.clientapp;
	exports org.unibl.etf.myapp.extended;
	requires transitive org.unibl.etf.util;
}
