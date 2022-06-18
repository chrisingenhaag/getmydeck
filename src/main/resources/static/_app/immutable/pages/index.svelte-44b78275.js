import{S as He,i as qe,s as je,e as n,t as _,k as g,c as i,a as d,h as v,d as r,m as p,b as s,g as me,K as e,n as pe,x as Be,J as ht,y as Ne,L as Ze,z as Re,M as Ve,N as $e,O as Ae,P as ut,Q as ct,r as Oe,p as Ce,C as Ge,R as et,T as ft,w as mt,U as tt}from"../chunks/index-a9450b1f.js";import{F as gt}from"../chunks/Footer-8d21d9ec.js";function pt(h){let t,l,a,u,c,b,f,k,V,j,y,G,W,H,w;return{c(){t=n("article"),l=n("h1"),a=_("How long to get my Steam Deck?"),u=g(),c=n("p"),b=_("Inspired from the reddit Steam Deck Order Email Megathreads "),f=n("a"),k=_("(list of them here)"),V=_(` 
    I wanted to give an alternative way of getting actual information
    besides talking to the deckbot within the reddit.`),j=g(),y=n("p"),G=_(`Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
    and making the data available. 
    This site also relies on the data the people share in the reddit to 
    collect information which people are able to
    order their steam deck based on the reservation time, their region and their version.`),W=g(),H=n("p"),w=_(`Here you just need to enter your data to get your information about how far 
    away you might be from ordering your steam deck. Every week 
    you can just come back and see if something has changed (without a reddit 
    account and talking to deckbot every week).`),this.h()},l(B){t=i(B,"ARTICLE",{class:!0});var D=d(t);l=i(D,"H1",{class:!0});var F=d(l);a=v(F,"How long to get my Steam Deck?"),F.forEach(r),u=p(D),c=i(D,"P",{});var E=d(c);b=v(E,"Inspired from the reddit Steam Deck Order Email Megathreads "),f=i(E,"A",{target:!0,href:!0});var Q=d(f);k=v(Q,"(list of them here)"),Q.forEach(r),V=v(E,` 
    I wanted to give an alternative way of getting actual information
    besides talking to the deckbot within the reddit.`),E.forEach(r),j=p(D),y=i(D,"P",{});var K=d(y);G=v(K,`Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
    and making the data available. 
    This site also relies on the data the people share in the reddit to 
    collect information which people are able to
    order their steam deck based on the reservation time, their region and their version.`),K.forEach(r),W=p(D),H=i(D,"P",{});var C=d(H);w=v(C,`Here you just need to enter your data to get your information about how far 
    away you might be from ordering your steam deck. Every week 
    you can just come back and see if something has changed (without a reddit 
    account and talking to deckbot every week).`),C.forEach(r),D.forEach(r),this.h()},h(){s(l,"class","text-center"),s(f,"target","_blank"),s(f,"href","https://www.reddit.com/r/SteamDeck/search?q=flair_name%3A%22MEGATHREAD%22&restrict_sr=1"),s(t,"class","")},m(B,D){me(B,t,D),e(t,l),e(l,a),e(t,u),e(t,c),e(c,b),e(c,f),e(f,k),e(c,V),e(t,j),e(t,y),e(y,G),e(t,W),e(t,H),e(H,w)},p:pe,i:pe,o:pe,d(B){B&&r(t)}}}class _t extends He{constructor(t){super(),qe(this,t,null,pt,je,{})}}function vt(h){let t,l,a,u,c,b,f,k,V,j,y,G,W,H,w,B,D,F,E,Q,K,C,N,q,_e,$,ne,ve,be,O,ge,ke,ie,X,z,de,le,ye,U,Z,ee,Ee,re,te,we,Y,ae,Ie,ce,oe,T,L,se,P,S,R,o,A,m;return{c(){t=n("div"),l=n("h4"),a=_("Changelog:"),u=g(),c=n("ul"),b=n("li"),f=_("June 18, 2022: Moved results to separate page for results being shareable via link"),k=g(),V=n("li"),j=_("June 17, 2022: Added graph toggle to show all or last 8 data sets"),y=g(),G=n("li"),W=_("June 17, 2022: Added graph toggle to auto y-axis and full 100% view"),H=g(),w=n("li"),B=_("June 15, 2022: Added graph for past percentages; Limit data points to mondays and thursdays"),D=g(),F=n("li"),E=_("June 12, 2022: Added collection of historical data, persistence and presentation of past percentages"),Q=g(),K=n("li"),C=_("May 31, 2022: Switched text response to be more understandable"),N=g(),q=n("li"),_e=_("May 30, 2022: Updated to auto fetch data from deckbot google sheet"),$=g(),ne=n("li"),ve=_("May 30, 2022: Updated data from deckbot"),be=g(),O=n("li"),ge=_("May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response"),ke=g(),ie=n("li"),X=_("May 26, 2022: Updated data from deckbot googlesheet"),z=g(),de=n("h4"),le=_("Todo:"),ye=g(),U=n("ul"),Z=n("li"),ee=_("Use historical data to calc a 5 week average and use it to calc estimated order date"),Ee=g(),re=n("li"),te=_("Make results shareable via link"),we=g(),Y=n("li"),ae=_("Improve graph and readability"),Ie=g(),ce=n("li"),oe=_("Use historical data to display last increases on your personal reservation time"),T=g(),L=n("li"),se=_("If deckbot data changes, persist them to disk. Load this data from disk"),P=g(),S=n("li"),R=_("Update data from deckbot datasource only if they changed"),o=g(),A=n("li"),m=_("Auto update data from deckbot datasource"),this.h()},l(J){t=i(J,"DIV",{class:!0});var x=d(t);l=i(x,"H4",{});var he=d(l);a=v(he,"Changelog:"),he.forEach(r),u=p(x),c=i(x,"UL",{});var I=d(c);b=i(I,"LI",{});var Me=d(b);f=v(Me,"June 18, 2022: Moved results to separate page for results being shareable via link"),Me.forEach(r),k=p(I),V=i(I,"LI",{});var ue=d(V);j=v(ue,"June 17, 2022: Added graph toggle to show all or last 8 data sets"),ue.forEach(r),y=p(I),G=i(I,"LI",{});var Ue=d(G);W=v(Ue,"June 17, 2022: Added graph toggle to auto y-axis and full 100% view"),Ue.forEach(r),H=p(I),w=i(I,"LI",{});var xe=d(w);B=v(xe,"June 15, 2022: Added graph for past percentages; Limit data points to mondays and thursdays"),xe.forEach(r),D=p(I),F=i(I,"LI",{});var fe=d(F);E=v(fe,"June 12, 2022: Added collection of historical data, persistence and presentation of past percentages"),fe.forEach(r),Q=p(I),K=i(I,"LI",{});var Le=d(K);C=v(Le,"May 31, 2022: Switched text response to be more understandable"),Le.forEach(r),N=p(I),q=i(I,"LI",{});var De=d(q);_e=v(De,"May 30, 2022: Updated to auto fetch data from deckbot google sheet"),De.forEach(r),$=p(I),ne=i(I,"LI",{});var Se=d(ne);ve=v(Se,"May 30, 2022: Updated data from deckbot"),Se.forEach(r),be=p(I),O=i(I,"LI",{});var Te=d(O);ge=v(Te,"May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response"),Te.forEach(r),ke=p(I),ie=i(I,"LI",{});var Pe=d(ie);X=v(Pe,"May 26, 2022: Updated data from deckbot googlesheet"),Pe.forEach(r),I.forEach(r),z=p(x),de=i(x,"H4",{});var Je=d(de);le=v(Je,"Todo:"),Je.forEach(r),ye=p(x),U=i(x,"UL",{});var M=d(U);Z=i(M,"LI",{});var Fe=d(Z);ee=v(Fe,"Use historical data to calc a 5 week average and use it to calc estimated order date"),Fe.forEach(r),Ee=p(M),re=i(M,"LI",{class:!0});var Ke=d(re);te=v(Ke,"Make results shareable via link"),Ke.forEach(r),we=p(M),Y=i(M,"LI",{class:!0});var ze=d(Y);ae=v(ze,"Improve graph and readability"),ze.forEach(r),Ie=p(M),ce=i(M,"LI",{class:!0});var Ye=d(ce);oe=v(Ye,"Use historical data to display last increases on your personal reservation time"),Ye.forEach(r),T=p(M),L=i(M,"LI",{class:!0});var We=d(L);se=v(We,"If deckbot data changes, persist them to disk. Load this data from disk"),We.forEach(r),P=p(M),S=i(M,"LI",{class:!0});var Qe=d(S);R=v(Qe,"Update data from deckbot datasource only if they changed"),Qe.forEach(r),o=p(M),A=i(M,"LI",{class:!0});var Xe=d(A);m=v(Xe,"Auto update data from deckbot datasource"),Xe.forEach(r),M.forEach(r),x.forEach(r),this.h()},h(){s(re,"class","line-through"),s(Y,"class","line-through"),s(ce,"class","line-through"),s(L,"class","line-through"),s(S,"class","line-through"),s(A,"class","line-through"),s(t,"class","block border-t-2 text-xs")},m(J,x){me(J,t,x),e(t,l),e(l,a),e(t,u),e(t,c),e(c,b),e(b,f),e(c,k),e(c,V),e(V,j),e(c,y),e(c,G),e(G,W),e(c,H),e(c,w),e(w,B),e(c,D),e(c,F),e(F,E),e(c,Q),e(c,K),e(K,C),e(c,N),e(c,q),e(q,_e),e(c,$),e(c,ne),e(ne,ve),e(c,be),e(c,O),e(O,ge),e(c,ke),e(c,ie),e(ie,X),e(t,z),e(t,de),e(de,le),e(t,ye),e(t,U),e(U,Z),e(Z,ee),e(U,Ee),e(U,re),e(re,te),e(U,we),e(U,Y),e(Y,ae),e(U,Ie),e(U,ce),e(ce,oe),e(U,T),e(U,L),e(L,se),e(U,P),e(U,S),e(S,R),e(U,o),e(U,A),e(A,m)},p:pe,i:pe,o:pe,d(J){J&&r(t)}}}class bt extends He{constructor(t){super(),qe(this,t,null,vt,je,{})}}function at(h,t,l){const a=h.slice();return a[19]=t[l],a}function lt(h,t,l){const a=h.slice();return a[22]=t[l],a}function rt(h){let t,l=h[22].text+"",a,u,c,b;return{c(){t=n("option"),a=_(l),u=g(),this.h()},l(f){t=i(f,"OPTION",{label:!0});var k=d(t);a=v(k,l),u=p(k),k.forEach(r),this.h()},h(){s(t,"label",c=h[22].text),t.__value=b=h[22].value,t.value=t.__value},m(f,k){me(f,t,k),e(t,a),e(t,u)},p:pe,d(f){f&&r(t)}}}function ot(h){let t,l;return{c(){t=n("span"),l=_("Please select a region"),this.h()},l(a){t=i(a,"SPAN",{class:!0});var u=d(t);l=v(u,"Please select a region"),u.forEach(r),this.h()},h(){s(t,"class","text-sm text-red-600 dark:text-red-500")},m(a,u){me(a,t,u),e(t,l)},d(a){a&&r(t)}}}function st(h){let t,l=h[19].text+"",a,u,c,b;return{c(){t=n("option"),a=_(l),u=g(),this.h()},l(f){t=i(f,"OPTION",{label:!0});var k=d(t);a=v(k,l),u=p(k),k.forEach(r),this.h()},h(){s(t,"label",c=h[19].text),t.__value=b=h[19].value,t.value=t.__value},m(f,k){me(f,t,k),e(t,a),e(t,u)},p:pe,d(f){f&&r(t)}}}function nt(h){let t,l;return{c(){t=n("span"),l=_("Please select a version"),this.h()},l(a){t=i(a,"SPAN",{class:!0});var u=d(t);l=v(u,"Please select a version"),u.forEach(r),this.h()},h(){s(t,"class","text-sm text-red-600 dark:text-red-500")},m(a,u){me(a,t,u),e(t,l)},d(a){a&&r(t)}}}function it(h){let t,l;return{c(){t=n("span"),l=_("Please enter a valid timestamp (10 digits)"),this.h()},l(a){t=i(a,"SPAN",{class:!0});var u=d(t);l=v(u,"Please enter a valid timestamp (10 digits)"),u.forEach(r),this.h()},h(){s(t,"class","text-sm text-red-600 dark:text-red-500")},m(a,u){me(a,t,u),e(t,l)},d(a){a&&r(t)}}}function kt(h){let t,l,a,u,c,b,f,k,V,j,y,G,W,H,w,B,D,F,E,Q,K,C,N,q,_e,$,ne,ve,be,O,ge,ke,ie,X,z,de,le,ye,U,Z,ee,Ee,re,te,we,Y,ae,Ie,ce;u=new _t({});let oe=h[7],T=[];for(let o=0;o<oe.length;o+=1)T[o]=rt(lt(h,oe,o));let L=!h[4]&&ot(),se=h[8],P=[];for(let o=0;o<se.length;o+=1)P[o]=st(at(h,se,o));let S=!h[5]&&nt(),R=!h[3]&&it();return te=new bt({}),Y=new gt({}),{c(){t=g(),l=n("div"),a=n("div"),Be(u.$$.fragment),c=g(),b=n("form"),f=n("div"),k=n("label"),V=_("In which region did you preorder your Steam Deck?"),j=g(),y=n("select");for(let o=0;o<T.length;o+=1)T[o].c();W=g(),L&&L.c(),H=g(),w=n("div"),B=n("label"),D=_("Which version did you reserve?"),F=g(),E=n("select");for(let o=0;o<P.length;o+=1)P[o].c();K=g(),S&&S.c(),C=g(),N=n("div"),q=n("label"),_e=_(`Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the `),$=n("a"),ne=_("reddit DeckBot description"),ve=_("."),be=g(),O=n("input"),ke=g(),R&&R.c(),ie=g(),X=n("label"),z=n("input"),de=g(),le=n("span"),ye=_("Remember me"),U=g(),Z=n("label"),ee=n("button"),Ee=_("Get my current preorder status"),re=g(),Be(te.$$.fragment),we=g(),Be(Y.$$.fragment),this.h()},l(o){ht('[data-svelte="svelte-gggq5d"]',document.head).forEach(r),t=p(o),l=i(o,"DIV",{class:!0});var m=d(l);a=i(m,"DIV",{class:!0});var J=d(a);Ne(u.$$.fragment,J),c=p(J),b=i(J,"FORM",{});var x=d(b);f=i(x,"DIV",{class:!0});var he=d(f);k=i(he,"LABEL",{for:!0,class:!0});var I=d(k);V=v(I,"In which region did you preorder your Steam Deck?"),I.forEach(r),j=p(he),y=i(he,"SELECT",{class:!0,id:!0,name:!0});var Me=d(y);for(let M=0;M<T.length;M+=1)T[M].l(Me);Me.forEach(r),W=p(he),L&&L.l(he),he.forEach(r),H=p(x),w=i(x,"DIV",{class:!0});var ue=d(w);B=i(ue,"LABEL",{for:!0,class:!0});var Ue=d(B);D=v(Ue,"Which version did you reserve?"),Ue.forEach(r),F=p(ue),E=i(ue,"SELECT",{class:!0,id:!0,name:!0});var xe=d(E);for(let M=0;M<P.length;M+=1)P[M].l(xe);xe.forEach(r),K=p(ue),S&&S.l(ue),ue.forEach(r),C=p(x),N=i(x,"DIV",{class:!0});var fe=d(N);q=i(fe,"LABEL",{for:!0,class:!0});var Le=d(q);_e=v(Le,`Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the `),$=i(Le,"A",{target:!0,href:!0});var De=d($);ne=v(De,"reddit DeckBot description"),De.forEach(r),ve=v(Le,"."),Le.forEach(r),be=p(fe),O=i(fe,"INPUT",{type:!0,class:!0,name:!0,id:!0}),ke=p(fe),R&&R.l(fe),fe.forEach(r),ie=p(x),X=i(x,"LABEL",{class:!0});var Se=d(X);z=i(Se,"INPUT",{type:!0,class:!0,name:!0,id:!0}),de=p(Se),le=i(Se,"SPAN",{class:!0});var Te=d(le);ye=v(Te,"Remember me"),Te.forEach(r),Se.forEach(r),U=p(x),Z=i(x,"LABEL",{class:!0});var Pe=d(Z);ee=i(Pe,"BUTTON",{class:!0,type:!0});var Je=d(ee);Ee=v(Je,"Get my current preorder status"),Je.forEach(r),Pe.forEach(r),x.forEach(r),re=p(J),Ne(te.$$.fragment,J),we=p(J),Ne(Y.$$.fragment,J),J.forEach(r),m.forEach(r),this.h()},h(){document.title="GetMyDeck - Calculator",s(k,"for","region"),s(k,"class","text-gray-700"),s(y,"class",G="form-select block rounded-md shadow-sm w-full mt-1 "+(h[4]?"":"bg-red-50 border border-red-500 text-red-900")),s(y,"id","region"),s(y,"name","region"),h[1]===void 0&&Ze(()=>h[10].call(y)),s(f,"class",""),s(B,"for","version"),s(B,"class","text-gray-700"),s(E,"class",Q="form-select block rounded-md shadow-sm w-full mt-1 "+(h[5]?"":"bg-red-50 border border-red-500 text-red-900")),s(E,"id","version"),s(E,"name","version"),h[2]===void 0&&Ze(()=>h[12].call(E)),s(w,"class",""),s($,"target","_blank"),s($,"href","https://www.reddit.com/r/SteamDeck/comments/ui642q/introducing_deckbot/"),s(q,"for","reserationTime"),s(q,"class","text-gray-700"),s(O,"type","number"),s(O,"class",ge="form-input block rounded-md shadow-sm w-full mt-1 "+(h[3]?"":"bg-red-50 border border-red-500 text-red-900")),s(O,"name","reservationTime"),s(O,"id","reserationTime"),s(N,"class",""),s(z,"type","checkbox"),s(z,"class","form-input rounded-md shadow-sm"),s(z,"name","rememberme"),s(z,"id","rememberme"),s(le,"class","text-gray-700"),s(X,"class","block mt-3"),s(ee,"class","px-4 py-2 mt-5 font-semibold text-sm bg-sky-900 active:bg-sky-600 text-white rounded-lg shadow-sm"),s(ee,"type","submit"),s(Z,"class",""),s(a,"class","grid grid-cols-1 gap-6 content-center"),s(l,"class","container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose")},m(o,A){me(o,t,A),me(o,l,A),e(l,a),Re(u,a,null),e(a,c),e(a,b),e(b,f),e(f,k),e(k,V),e(f,j),e(f,y);for(let m=0;m<T.length;m+=1)T[m].m(y,null);Ve(y,h[1]),e(f,W),L&&L.m(f,null),e(b,H),e(b,w),e(w,B),e(B,D),e(w,F),e(w,E);for(let m=0;m<P.length;m+=1)P[m].m(E,null);Ve(E,h[2]),e(w,K),S&&S.m(w,null),e(b,C),e(b,N),e(N,q),e(q,_e),e(q,$),e($,ne),e(q,ve),e(N,be),e(N,O),$e(O,h[0]),e(N,ke),R&&R.m(N,null),e(b,ie),e(b,X),e(X,z),z.checked=h[6],e(X,de),e(X,le),e(le,ye),e(b,U),e(b,Z),e(Z,ee),e(ee,Ee),e(a,re),Re(te,a,null),e(a,we),Re(Y,a,null),ae=!0,Ie||(ce=[Ae(y,"change",h[10]),Ae(y,"change",h[11]),Ae(E,"change",h[12]),Ae(E,"change",h[13]),Ae(O,"input",h[14]),Ae(z,"change",h[15]),Ae(b,"submit",ut(h[9]))],Ie=!0)},p(o,[A]){if(A&128){oe=o[7];let m;for(m=0;m<oe.length;m+=1){const J=lt(o,oe,m);T[m]?T[m].p(J,A):(T[m]=rt(J),T[m].c(),T[m].m(y,null))}for(;m<T.length;m+=1)T[m].d(1);T.length=oe.length}if((!ae||A&16&&G!==(G="form-select block rounded-md shadow-sm w-full mt-1 "+(o[4]?"":"bg-red-50 border border-red-500 text-red-900")))&&s(y,"class",G),A&130&&Ve(y,o[1]),o[4]?L&&(L.d(1),L=null):L||(L=ot(),L.c(),L.m(f,null)),A&256){se=o[8];let m;for(m=0;m<se.length;m+=1){const J=at(o,se,m);P[m]?P[m].p(J,A):(P[m]=st(J),P[m].c(),P[m].m(E,null))}for(;m<P.length;m+=1)P[m].d(1);P.length=se.length}(!ae||A&32&&Q!==(Q="form-select block rounded-md shadow-sm w-full mt-1 "+(o[5]?"":"bg-red-50 border border-red-500 text-red-900")))&&s(E,"class",Q),A&260&&Ve(E,o[2]),o[5]?S&&(S.d(1),S=null):S||(S=nt(),S.c(),S.m(w,null)),(!ae||A&8&&ge!==(ge="form-input block rounded-md shadow-sm w-full mt-1 "+(o[3]?"":"bg-red-50 border border-red-500 text-red-900")))&&s(O,"class",ge),A&1&&ct(O.value)!==o[0]&&$e(O,o[0]),o[3]?R&&(R.d(1),R=null):R||(R=it(),R.c(),R.m(N,null)),A&64&&(z.checked=o[6])},i(o){ae||(Oe(u.$$.fragment,o),Oe(te.$$.fragment,o),Oe(Y.$$.fragment,o),ae=!0)},o(o){Ce(u.$$.fragment,o),Ce(te.$$.fragment,o),Ce(Y.$$.fragment,o),ae=!1},d(o){o&&r(t),o&&r(l),Ge(u),et(T,o),L&&L.d(),et(P,o),S&&S.d(),R&&R.d(),Ge(te),Ge(Y),Ie=!1,ft(ce)}}}const dt="urn:getmydeck:rememberme";function yt(h,t,l){let a,u,c,b=!0,f=!0,k=!0,V=!1,j=[{id:0,text:"empty",value:void 0},{id:1,text:"US",value:"US"},{id:2,text:"UK",value:"UK"},{id:3,text:"EU",value:"EU"}],y=[{id:0,text:"empty",value:void 0},{id:1,text:"64GB",value:"64"},{id:2,text:"256GB",value:"256"},{id:3,text:"512GB",value:"512"}];function G(){l(4,f=!0),l(5,k=!0),l(3,b=!0)}function W(){return G(),u===void 0&&l(4,f=!1),c===void 0&&l(5,k=!1),(a==null||a.toString().length!==10)&&l(3,b=!1),f&&k&&b}function H(){if(W()){const C=`s/${u}/${c}/${a}`;V===!0?w():localStorage.clear(),window.location.assign(C)}}let w=()=>{let C={region:u,version:c,timestamp:a};localStorage.setItem(dt,JSON.stringify(C))};mt(async()=>{let C=localStorage.getItem(dt);if(C!==null){let N=JSON.parse(C);l(0,a=N.timestamp),l(1,u=N.region),l(2,c=N.version),l(6,V=!0)}});function B(){u=tt(this),l(1,u),l(7,j)}const D=()=>l(4,f=!0);function F(){c=tt(this),l(2,c),l(8,y)}const E=()=>l(5,k=!0);function Q(){a=ct(this.value),l(0,a)}function K(){V=this.checked,l(6,V)}return[a,u,c,b,f,k,V,j,y,H,B,D,F,E,Q,K]}class It extends He{constructor(t){super(),qe(this,t,yt,kt,je,{})}}export{It as default};