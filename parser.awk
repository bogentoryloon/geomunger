BEGIN 	{state=0}
/^[0-9]{5}$/ {
		gp=$0;
		state=1
	}
/^$/ 	{
#print "NL" state $0
	if(state==1) {
		state=2;
	}else if(state==2){
		state=3;
	}else if(state==3){
		state=0;
		printf("{\"id\":\"%s\",\"name\":\"%s\",\"address\":\"%s\"}\n",gp,doc,adr);
	}
}
/^DR/	{
#print "DR" state $0
	if(state==2){
		doc=$0;
		adr="";
		state=2}
	}
/^\w+/{
#print "ADR" state $0
	if(state==3){adr=adr $0}
	}
