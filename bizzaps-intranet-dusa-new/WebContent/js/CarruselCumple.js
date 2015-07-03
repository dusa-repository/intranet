
var DP2 = {
	ds: '',
	dA: 0,
	sD: 0,
	tD: 0,
	t: 2500,
	idD: 'diapos2',
	nID: 'diapos-on',
	idC: 'contador',
	clC: 'actual',
	IE: false,
	anim: true,
	intv: '',
	lnz: '',
	$: function(el){
		var e = document.getElementById(el); return e;
	},
	op: function(el,v){
		(DP2.IE) ? el.style.filter='alpha(opacity='+v+')' : el.setAttribute('style','opacity:'+v/100);
	},
	ac: function(c){
		DP2.$(DP2.idC).getElementsByTagName('li')[DP2.dA].className=c;	
	},
	fun: function(){
		var i = 100;
		(DP2.dA==DP2.tD) ? DP2.sD = 0 : DP2.sD = DP2.dA+1;
		DP2.intv = setInterval(function(){
			i = i-5;
			if(i>=0){
				DP2.op(DP2.ds[DP2.dA],i);
				DP2.op(DP2.ds[DP2.sD],(100-i));
			} else {
				DP2.ac('');
				(DP2.dA==DP2.tD) ? DP2.dA = 0 : DP2.dA++;
				DP2.ac(DP2.clC);
				clearInterval(DP2.intv);
				if(DP2.anim){DP2.lnz = setTimeout(DP2.fun,DP2.t);}
			}
		},50);
	},
	manual: function(d){
		clearInterval(DP2.intv);clearTimeout(DP2.lnz);DP2.anim=false;
		DP2.op(DP2.ds[DP2.dA],0);
		DP2.op(DP2.ds[d],100);
		DP2.ac('');
		DP2.dA=d;
		DP2.ac(DP2.clC);
	},
	inicio2: function(){
		(navigator.userAgent.match('MSIE')) ? DP2.IE = true : DP2.IE = false; 
		DP2.$(DP2.idD).id=DP2.nID;
		DP2.ds = DP2.$(DP2.nID).getElementsByTagName('li');
		DP2.tD = DP2.ds.length-1;
		var ct = document.createElement('ul');
		ct.id = DP2.idC;
		(DP2.$(DP2.nID).nextSibling) ? (DP2.$(DP2.nID).parentNode).insertBefore(ct,DP2.$(DP2.nID).nextSibling) : (DP2.$(DP2.nID).parentNode).appendChild(ct);
		for(var i=0;i<=DP2.tD;i++){
			DP2.op(DP2.ds[i],0);
			DP2.$(DP2.idC).innerHTML += '<li><a href="#" onclick="DP.manual('+i+')">'+(i+1)+'</a></li>';
		}
		DP2.op(DP2.ds[0],100);
		DP2.ac(DP2.clC);
		DP2.lnz = setTimeout(DP2.fun,DP2.t);
	}			
}