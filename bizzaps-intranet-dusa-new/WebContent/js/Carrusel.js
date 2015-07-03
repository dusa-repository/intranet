var DP = {
		ds: '',
		dA: 0,
		sD: 0,
		tD: 0,
		t: 3000,
		idD: 'diapos',
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
			(DP.IE) ? el.style.filter='alpha(opacity='+v+')' : el.setAttribute('style','opacity:'+v/100);
		},
		ac: function(c){
			DP.$(DP.idC).getElementsByTagName('li')[DP.dA].className=c;	
		},
		fun: function(){
			var i = 100;
			(DP.dA==DP.tD) ? DP.sD = 0 : DP.sD = DP.dA+1;
			DP.intv = setInterval(function(){
				i = i-5;
				if(i>=0){
					DP.op(DP.ds[DP.dA],i);
					DP.op(DP.ds[DP.sD],(100-i));
				} else {
					DP.ac('');
					(DP.dA==DP.tD) ? DP.dA = 0 : DP.dA++;
					DP.ac(DP.clC);
					clearInterval(DP.intv);
					if(DP.anim){DP.lnz = setTimeout(DP.fun,DP.t);}
				}
			},50);
		},
		manual: function(d){
			clearInterval(DP.intv);clearTimeout(DP.lnz);DP.anim=false;
			DP.op(DP.ds[DP.dA],0);
			DP.op(DP.ds[d],100);
			DP.ac('');
			DP.dA=d;
			DP.ac(DP.clC);
		},
		inicio: function(){
			(navigator.userAgent.match('MSIE')) ? DP.IE = true : DP.IE = false; 
			DP.$(DP.idD).id=DP.nID;
			DP.ds = DP.$(DP.nID).getElementsByTagName('li');
			DP.tD = DP.ds.length-1;
			var ct = document.createElement('ul');
			ct.id = DP.idC;
			(DP.$(DP.nID).nextSibling) ? (DP.$(DP.nID).parentNode).insertBefore(ct,DP.$(DP.nID).nextSibling) : (DP.$(DP.nID).parentNode).appendChild(ct);
			for(var i=0;i<=DP.tD;i++){
				DP.op(DP.ds[i],0);
				DP.$(DP.idC).innerHTML += '<li><a href="#" onclick="DP.manual('+i+')">'+(i+1)+'</a></li>';
			}
			DP.op(DP.ds[0],100);
			DP.ac(DP.clC);
			DP.lnz = setTimeout(DP.fun,DP.t);
		}			
	}