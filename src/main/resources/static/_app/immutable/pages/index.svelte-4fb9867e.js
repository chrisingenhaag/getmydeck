import{S as ya,i as Ea,s as wa,e as n,t as u,k as b,c as i,a as d,h as m,m as k,d as r,b as c,g as H,J as e,n as Me,l as na,K as ia,L as Ge,M as da,N as Pe,O as Ia,P as ka,Q as At,R as La,w as Da,j as je,T as Sa,U as Ua,V as ca}from"../chunks/index-4a8235da.js";function ha(s,t,l){const a=s.slice();return a[18]=t[l],a[20]=l,a}function fa(s,t,l){const a=s.slice();return a[21]=t[l],a}function ua(s,t,l){const a=s.slice();return a[24]=t[l],a}function ma(s){let t,l=s[24].text+"",a,o,_,y;return{c(){t=n("option"),a=u(l),o=b(),this.h()},l(g){t=i(g,"OPTION",{label:!0});var v=d(t);a=m(v,l),o=k(v),v.forEach(r),this.h()},h(){c(t,"label",_=s[24].text),t.__value=y=s[24].value,t.value=t.__value},m(g,v){H(g,t,v),e(t,a),e(t,o)},p:Me,d(g){g&&r(t)}}}function _a(s){let t,l=s[21].text+"",a,o,_,y;return{c(){t=n("option"),a=u(l),o=b(),this.h()},l(g){t=i(g,"OPTION",{label:!0});var v=d(t);a=m(v,l),o=k(v),v.forEach(r),this.h()},h(){c(t,"label",_=s[21].text),t.__value=y=s[21].value,t.value=t.__value},m(g,v){H(g,t,v),e(t,a),e(t,o)},p:Me,d(g){g&&r(t)}}}function pa(s){let t,l;return{c(){t=n("p"),l=u("Please fill out form completely")},l(a){t=i(a,"P",{});var o=d(t);l=m(o,"Please fill out form completely"),o.forEach(r)},m(a,o){H(a,t,o),e(t,l)},d(a){a&&r(t)}}}function va(s){let t;function l(_,y){return _[8]?Ma:_[5]?Pa:Ta}let a=l(s),o=a(s);return{c(){o.c(),t=na()},l(_){o.l(_),t=na()},m(_,y){o.m(_,y),H(_,t,y)},p(_,y){a===(a=l(_))&&o?o.p(_,y):(o.d(1),o=a(_),o&&(o.c(),o.m(t.parentNode,t)))},d(_){o.d(_),_&&r(t)}}}function Ta(s){let t,l;return{c(){t=n("p"),l=u("Fetching infos ...")},l(a){t=i(a,"P",{});var o=d(t);l=m(o,"Fetching infos ..."),o.forEach(r)},m(a,o){H(a,t,o),e(t,l)},p:Me,d(a){a&&r(t)}}}function Pa(s){let t,l=s[5].personalInfo.htmlText+"",a,o,_,y,g,v,w,C,M,V=s[5].personalInfo.historicData,I=[];for(let h=0;h<V.length;h+=1)I[h]=ga(ha(s,V,h));return{c(){t=new Sa(!1),a=b(),o=n("p"),_=u("Data last updated from deckbot sheet: "),y=u(s[6]),g=b(),v=n("h4"),w=u("Past percentages"),C=b(),M=n("ul");for(let h=0;h<I.length;h+=1)I[h].c();this.h()},l(h){t=Ua(h,!1),a=k(h),o=i(h,"P",{class:!0});var L=d(o);_=m(L,"Data last updated from deckbot sheet: "),y=m(L,s[6]),L.forEach(r),g=k(h),v=i(h,"H4",{});var E=d(v);w=m(E,"Past percentages"),E.forEach(r),C=k(h),M=i(h,"UL",{});var F=d(M);for(let Z=0;Z<I.length;Z+=1)I[Z].l(F);F.forEach(r),this.h()},h(){t.a=a,c(o,"class","text-xs")},m(h,L){t.m(l,h,L),H(h,a,L),H(h,o,L),e(o,_),e(o,y),H(h,g,L),H(h,v,L),e(v,w),H(h,C,L),H(h,M,L);for(let E=0;E<I.length;E+=1)I[E].m(M,null)},p(h,L){if(L&32&&l!==(l=h[5].personalInfo.htmlText+"")&&t.p(l),L&64&&je(y,h[6]),L&32){V=h[5].personalInfo.historicData;let E;for(E=0;E<V.length;E+=1){const F=ha(h,V,E);I[E]?I[E].p(F,L):(I[E]=ga(F),I[E].c(),I[E].m(M,null))}for(;E<I.length;E+=1)I[E].d(1);I.length=V.length}},d(h){h&&t.d(),h&&r(a),h&&r(o),h&&r(g),h&&r(v),h&&r(C),h&&r(M),At(I,h)}}}function Ma(s){let t,l;return{c(){t=n("p"),l=u(s[8])},l(a){t=i(a,"P",{});var o=d(t);l=m(o,s[8]),o.forEach(r)},m(a,o){H(a,t,o),e(t,l)},p(a,o){o&256&&je(l,a[8])},d(a){a&&r(t)}}}function ga(s){let t,l,a=s[18].date+"",o,_,y=s[18].elapsedTimePercentage+"",g;return{c(){t=n("li"),l=u("On "),o=u(a),_=u(" the percentage was "),g=u(y)},l(v){t=i(v,"LI",{});var w=d(t);l=m(w,"On "),o=m(w,a),_=m(w," the percentage was "),g=m(w,y),w.forEach(r)},m(v,w){H(v,t,w),e(t,l),e(t,o),e(t,_),e(t,g)},p(v,w){w&32&&a!==(a=v[18].date+"")&&je(o,a),w&32&&y!==(y=v[18].elapsedTimePercentage+"")&&je(g,y)},d(v){v&&r(t)}}}function Aa(s){let t,l,a,o,_,y,g,v,w,C,M,V,I,h,L,E,F,Z,S,Y,O,x,ge,D,Ce,ee,oe,qe,Ke,N,Fe,te,$,Je,re,Ye,ze,We,J,Qe,de,se,Xe,Ze,ae,z,xe,ce,$e,et,q,be,tt,at,Ae,lt,K,ke,ot,rt,G,ye,st,nt,Ee,it,dt,we,ct,ht,Ie,ft,ut,Le,mt,_t,De,pt,vt,j,Se,gt,bt,Ue,kt,yt,Te,Et,wt,he,It,Lt,fe,Dt,St,ue,me,Ut,ne,Tt,Pt,Ot,_e=s[9],U=[];for(let p=0;p<_e.length;p+=1)U[p]=ma(ua(s,_e,p));let pe=s[10],T=[];for(let p=0;p<pe.length;p+=1)T[p]=_a(fa(s,pe,p));let A=s[7]&&pa(),P=s[4]&&va(s);return{c(){t=n("div"),l=n("div"),a=n("article"),o=n("h1"),_=u("How long to get my Steam Deck?"),y=b(),g=n("p"),v=u("Inspired from the reddit Steam Deck Order Email Megathreads "),w=n("a"),C=u("(list of them here)"),M=u(` 
        I wanted to give an alternative way of getting actual information
        besides talking to the deckbot within the reddit.`),V=b(),I=n("p"),h=u(`Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
        and making the data available. 
        This site also relies on the data the people share in the reddit to 
        collect information which people are able to
        order their steam deck based on the reservation time, their region and their version.`),L=b(),E=n("p"),F=u(`Here you just need to enter your data to get your information about how far 
        away you might be from ordering your steam deck. Every week 
        you can just come back and see if something has changed (without a reddit 
        account and talking to deckbot every week).`),Z=b(),S=n("form"),Y=n("div"),O=n("label"),x=u("In which region did you preorder your Steam Deck?"),ge=b(),D=n("select");for(let p=0;p<U.length;p+=1)U[p].c();Ce=b(),ee=n("div"),oe=n("label"),qe=u("Which version did you reserve?"),Ke=b(),N=n("select");for(let p=0;p<T.length;p+=1)T[p].c();Fe=b(),te=n("div"),$=n("label"),Je=u(`Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the `),re=n("a"),Ye=u("reddit DeckBot description"),ze=u("."),We=b(),J=n("input"),Qe=b(),de=n("label"),se=n("button"),Xe=u("Get my current preorder status"),Ze=b(),ae=n("label"),z=n("input"),xe=b(),ce=n("span"),$e=u("Remember me"),et=b(),q=n("div"),be=n("h3"),tt=u("Results:"),at=b(),A&&A.c(),Ae=b(),P&&P.c(),lt=b(),K=n("div"),ke=n("h4"),ot=u("Changelog:"),rt=b(),G=n("ul"),ye=n("li"),st=u("May 31, 2022: Switched text response to be more understandable"),nt=b(),Ee=n("li"),it=u("May 30, 2022: Updated to auto fetch data from deckbot google sheet"),dt=b(),we=n("li"),ct=u("May 30, 2022: Updated data from deckbot"),ht=b(),Ie=n("li"),ft=u("May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response"),ut=b(),Le=n("li"),mt=u("May 26, 2022: Updated data from deckbot googlesheet"),_t=b(),De=n("h4"),pt=u("Todo:"),vt=b(),j=n("ul"),Se=n("li"),gt=u("Use historical data to calc a 5 week average and use it to calc estimated order date"),bt=b(),Ue=n("li"),kt=u("Use historical data to display last increases on your personal reservation time"),yt=b(),Te=n("li"),Et=u("If deckbot data changes, persist them to disk. Load this data from disk"),wt=b(),he=n("li"),It=u("Update data from deckbot datasource only if they changed"),Lt=b(),fe=n("li"),Dt=u("Auto update data from deckbot datasource"),St=b(),ue=n("div"),me=n("p"),Ut=u("If you have questions or comments feel free to reach out for me on reddit "),ne=n("a"),Tt=u("Labidou51"),this.h()},l(p){t=i(p,"DIV",{class:!0});var R=d(t);l=i(R,"DIV",{class:!0});var f=d(l);a=i(f,"ARTICLE",{class:!0});var B=d(a);o=i(B,"H1",{class:!0});var Rt=d(o);_=m(Rt,"How long to get my Steam Deck?"),Rt.forEach(r),y=k(B),g=i(B,"P",{});var Oe=d(g);v=m(Oe,"Inspired from the reddit Steam Deck Order Email Megathreads "),w=i(Oe,"A",{target:!0,href:!0});var Bt=d(w);C=m(Bt,"(list of them here)"),Bt.forEach(r),M=m(Oe,` 
        I wanted to give an alternative way of getting actual information
        besides talking to the deckbot within the reddit.`),Oe.forEach(r),V=k(B),I=i(B,"P",{});var Ht=d(I);h=m(Ht,`Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
        and making the data available. 
        This site also relies on the data the people share in the reddit to 
        collect information which people are able to
        order their steam deck based on the reservation time, their region and their version.`),Ht.forEach(r),L=k(B),E=i(B,"P",{});var Vt=d(E);F=m(Vt,`Here you just need to enter your data to get your information about how far 
        away you might be from ordering your steam deck. Every week 
        you can just come back and see if something has changed (without a reddit 
        account and talking to deckbot every week).`),Vt.forEach(r),B.forEach(r),Z=k(f),S=i(f,"FORM",{});var W=d(S);Y=i(W,"DIV",{class:!0});var Re=d(Y);O=i(Re,"LABEL",{for:!0,class:!0});var Nt=d(O);x=m(Nt,"In which region did you preorder your Steam Deck?"),Nt.forEach(r),ge=k(Re),D=i(Re,"SELECT",{class:!0,id:!0,name:!0});var Gt=d(D);for(let ie=0;ie<U.length;ie+=1)U[ie].l(Gt);Gt.forEach(r),Re.forEach(r),Ce=k(W),ee=i(W,"DIV",{class:!0});var Be=d(ee);oe=i(Be,"LABEL",{for:!0,class:!0});var jt=d(oe);qe=m(jt,"Which version did you reserve?"),jt.forEach(r),Ke=k(Be),N=i(Be,"SELECT",{class:!0,id:!0,name:!0});var Ct=d(N);for(let ie=0;ie<T.length;ie+=1)T[ie].l(Ct);Ct.forEach(r),Be.forEach(r),Fe=k(W),te=i(W,"DIV",{class:!0});var He=d(te);$=i(He,"LABEL",{for:!0,class:!0});var Ve=d($);Je=m(Ve,`Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the `),re=i(Ve,"A",{target:!0,href:!0});var qt=d(re);Ye=m(qt,"reddit DeckBot description"),qt.forEach(r),ze=m(Ve,"."),Ve.forEach(r),We=k(He),J=i(He,"INPUT",{type:!0,class:!0,name:!0,id:!0}),He.forEach(r),Qe=k(W),de=i(W,"LABEL",{class:!0});var Kt=d(de);se=i(Kt,"BUTTON",{class:!0,type:!0});var Ft=d(se);Xe=m(Ft,"Get my current preorder status"),Ft.forEach(r),Kt.forEach(r),Ze=k(W),ae=i(W,"LABEL",{class:!0});var Ne=d(ae);z=i(Ne,"INPUT",{type:!0,class:!0,name:!0,id:!0}),xe=k(Ne),ce=i(Ne,"SPAN",{class:!0});var Jt=d(ce);$e=m(Jt,"Remember me"),Jt.forEach(r),Ne.forEach(r),W.forEach(r),et=k(f),q=i(f,"DIV",{class:!0});var ve=d(q);be=i(ve,"H3",{});var Yt=d(be);tt=m(Yt,"Results:"),Yt.forEach(r),at=k(ve),A&&A.l(ve),Ae=k(ve),P&&P.l(ve),ve.forEach(r),lt=k(f),K=i(f,"DIV",{class:!0});var le=d(K);ke=i(le,"H4",{});var zt=d(ke);ot=m(zt,"Changelog:"),zt.forEach(r),rt=k(le),G=i(le,"UL",{});var Q=d(G);ye=i(Q,"LI",{});var Wt=d(ye);st=m(Wt,"May 31, 2022: Switched text response to be more understandable"),Wt.forEach(r),nt=k(Q),Ee=i(Q,"LI",{});var Qt=d(Ee);it=m(Qt,"May 30, 2022: Updated to auto fetch data from deckbot google sheet"),Qt.forEach(r),dt=k(Q),we=i(Q,"LI",{});var Xt=d(we);ct=m(Xt,"May 30, 2022: Updated data from deckbot"),Xt.forEach(r),ht=k(Q),Ie=i(Q,"LI",{});var Zt=d(Ie);ft=m(Zt,"May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response"),Zt.forEach(r),ut=k(Q),Le=i(Q,"LI",{});var xt=d(Le);mt=m(xt,"May 26, 2022: Updated data from deckbot googlesheet"),xt.forEach(r),Q.forEach(r),_t=k(le),De=i(le,"H4",{});var $t=d(De);pt=m($t,"Todo:"),$t.forEach(r),vt=k(le),j=i(le,"UL",{});var X=d(j);Se=i(X,"LI",{});var ea=d(Se);gt=m(ea,"Use historical data to calc a 5 week average and use it to calc estimated order date"),ea.forEach(r),bt=k(X),Ue=i(X,"LI",{});var ta=d(Ue);kt=m(ta,"Use historical data to display last increases on your personal reservation time"),ta.forEach(r),yt=k(X),Te=i(X,"LI",{});var aa=d(Te);Et=m(aa,"If deckbot data changes, persist them to disk. Load this data from disk"),aa.forEach(r),wt=k(X),he=i(X,"LI",{class:!0});var la=d(he);It=m(la,"Update data from deckbot datasource only if they changed"),la.forEach(r),Lt=k(X),fe=i(X,"LI",{class:!0});var oa=d(fe);Dt=m(oa,"Auto update data from deckbot datasource"),oa.forEach(r),X.forEach(r),le.forEach(r),St=k(f),ue=i(f,"DIV",{class:!0});var ra=d(ue);me=i(ra,"P",{});var Mt=d(me);Ut=m(Mt,"If you have questions or comments feel free to reach out for me on reddit "),ne=i(Mt,"A",{target:!0,href:!0});var sa=d(ne);Tt=m(sa,"Labidou51"),sa.forEach(r),Mt.forEach(r),ra.forEach(r),f.forEach(r),R.forEach(r),this.h()},h(){c(o,"class","text-center"),c(w,"target","_blank"),c(w,"href","https://www.reddit.com/r/SteamDeck/search?q=flair_name%3A%22MEGATHREAD%22&restrict_sr=1"),c(a,"class",""),c(O,"for","region"),c(O,"class","text-gray-700"),c(D,"class","form-select block rounded-md shadow-sm w-full mt-1"),c(D,"id","region"),c(D,"name","region"),s[1]===void 0&&ia(()=>s[12].call(D)),c(Y,"class",""),c(oe,"for","version"),c(oe,"class","text-gray-700"),c(N,"class","form-select block rounded-md shadow-sm w-full mt-1"),c(N,"id","version"),c(N,"name","version"),s[2]===void 0&&ia(()=>s[13].call(N)),c(ee,"class",""),c(re,"target","_blank"),c(re,"href","https://www.reddit.com/r/SteamDeck/comments/ui642q/introducing_deckbot/"),c($,"for","reserationTime"),c($,"class","text-gray-700"),c(J,"type","number"),c(J,"class","form-input block rounded-md shadow-sm w-full mt-1"),c(J,"name","reservationTime"),c(J,"id","reserationTime"),c(te,"class",""),c(se,"class","px-4 py-2 mt-5 font-semibold text-sm bg-sky-900 active:bg-sky-600 text-white rounded-lg shadow-sm"),c(se,"type","submit"),c(de,"class",""),c(z,"type","checkbox"),c(z,"class","form-input rounded-md shadow-sm"),c(z,"name","rememberme"),c(z,"id","rememberme"),c(ce,"class","text-gray-700"),c(ae,"class","block mt-3"),c(q,"class","block border-t-2"),c(he,"class","line-through"),c(fe,"class","line-through"),c(K,"class","block border-t-2 text-xs"),c(ne,"target","_blank"),c(ne,"href","https://www.reddit.com/u/Labidou51"),c(ue,"class","block border-t-2"),c(l,"class","grid grid-cols-1 gap-6 content-center"),c(t,"class","container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose")},m(p,R){H(p,t,R),e(t,l),e(l,a),e(a,o),e(o,_),e(a,y),e(a,g),e(g,v),e(g,w),e(w,C),e(g,M),e(a,V),e(a,I),e(I,h),e(a,L),e(a,E),e(E,F),e(l,Z),e(l,S),e(S,Y),e(Y,O),e(O,x),e(Y,ge),e(Y,D);for(let f=0;f<U.length;f+=1)U[f].m(D,null);Ge(D,s[1]),e(S,Ce),e(S,ee),e(ee,oe),e(oe,qe),e(ee,Ke),e(ee,N);for(let f=0;f<T.length;f+=1)T[f].m(N,null);Ge(N,s[2]),e(S,Fe),e(S,te),e(te,$),e($,Je),e($,re),e(re,Ye),e($,ze),e(te,We),e(te,J),da(J,s[0]),e(S,Qe),e(S,de),e(de,se),e(se,Xe),e(S,Ze),e(S,ae),e(ae,z),z.checked=s[3],e(ae,xe),e(ae,ce),e(ce,$e),e(l,et),e(l,q),e(q,be),e(be,tt),e(q,at),A&&A.m(q,null),e(q,Ae),P&&P.m(q,null),e(l,lt),e(l,K),e(K,ke),e(ke,ot),e(K,rt),e(K,G),e(G,ye),e(ye,st),e(G,nt),e(G,Ee),e(Ee,it),e(G,dt),e(G,we),e(we,ct),e(G,ht),e(G,Ie),e(Ie,ft),e(G,ut),e(G,Le),e(Le,mt),e(K,_t),e(K,De),e(De,pt),e(K,vt),e(K,j),e(j,Se),e(Se,gt),e(j,bt),e(j,Ue),e(Ue,kt),e(j,yt),e(j,Te),e(Te,Et),e(j,wt),e(j,he),e(he,It),e(j,Lt),e(j,fe),e(fe,Dt),e(l,St),e(l,ue),e(ue,me),e(me,Ut),e(me,ne),e(ne,Tt),Pt||(Ot=[Pe(D,"change",s[12]),Pe(N,"change",s[13]),Pe(J,"input",s[14]),Pe(z,"change",s[15]),Pe(S,"submit",Ia(s[11]))],Pt=!0)},p(p,[R]){if(R&512){_e=p[9];let f;for(f=0;f<_e.length;f+=1){const B=ua(p,_e,f);U[f]?U[f].p(B,R):(U[f]=ma(B),U[f].c(),U[f].m(D,null))}for(;f<U.length;f+=1)U[f].d(1);U.length=_e.length}if(R&514&&Ge(D,p[1]),R&1024){pe=p[10];let f;for(f=0;f<pe.length;f+=1){const B=fa(p,pe,f);T[f]?T[f].p(B,R):(T[f]=_a(B),T[f].c(),T[f].m(N,null))}for(;f<T.length;f+=1)T[f].d(1);T.length=pe.length}R&1028&&Ge(N,p[2]),R&1&&ka(J.value)!==p[0]&&da(J,p[0]),R&8&&(z.checked=p[3]),p[7]?A||(A=pa(),A.c(),A.m(q,Ae)):A&&(A.d(1),A=null),p[4]?P?P.p(p,R):(P=va(p),P.c(),P.m(q,null)):P&&(P.d(1),P=null)},i:Me,o:Me,d(p){p&&r(t),At(U,p),At(T,p),A&&A.d(),P&&P.d(),Pt=!1,La(Ot)}}}const ba="urn:getmydeck:rememberme";function Oa(s,t,l){let a,o,_,y=!1,g=!1,v,w,C=!1,M,V=[{id:0,text:"empty",value:void 0},{id:1,text:"US",value:"US"},{id:2,text:"UK",value:"UK"},{id:3,text:"EU",value:"EU"}],I=[{id:0,text:"empty",value:void 0},{id:1,text:"64GB",value:"64"},{id:2,text:"256GB",value:"256"},{id:3,text:"512GB",value:"512"}];function h(){o!==void 0&&_!==void 0&&a!=null?(E(o,_,a),l(7,C=!1),l(4,g=!0),y===!0?L():localStorage.clear()):(l(7,C=!0),l(4,g=!1))}let L=()=>{let O={region:o,version:_,timestamp:a};localStorage.setItem(ba,JSON.stringify(O))},E=async(O,x,ge)=>{l(8,M=""),await fetch(`/api/v2/regions/${O}/versions/${x}/infos/${ge}`).then(D=>D.json()).then(D=>{l(5,v=D)}).catch(()=>{l(8,M="Problem loading infos. Please fix your inputs.")}).finally(()=>{let D=new Date(v.officialInfo.lastDataUpdate);l(6,w=D.toLocaleString()),l(4,g=!0)})};Da(async()=>{let O=localStorage.getItem(ba);if(O!==null){let x=JSON.parse(O);l(0,a=x.timestamp),l(1,o=x.region),l(2,_=x.version),l(3,y=!0),E(o,_,a)}});function F(){o=ca(this),l(1,o),l(9,V)}function Z(){_=ca(this),l(2,_),l(10,I)}function S(){a=ka(this.value),l(0,a)}function Y(){y=this.checked,l(3,y)}return[a,o,_,y,g,v,w,C,M,V,I,h,F,Z,S,Y]}class Ba extends ya{constructor(t){super(),Ea(this,t,Oa,Aa,wa,{})}}export{Ba as default};
