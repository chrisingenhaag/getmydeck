import{S as Zt,i as $t,s as ea,e as s,t as _,k as b,c as n,a as c,h as v,m as k,d as r,g as C,J as e,n as Se,l as Vt,b as f,K as jt,L as Me,M as Ct,N as Ie,O as ta,P as Xt,Q as qt,R as aa,w as la,j as mt,T as Kt}from"../chunks/index-a7ad466d.js";function Ft(d,t,l){const a=d.slice();return a[17]=t[l],a}function Jt(d,t,l){const a=d.slice();return a[20]=t[l],a}function Yt(d){let t,l=d[20].text+"",a,o,h;return{c(){t=s("option"),a=_(l),o=b(),this.h()},l(m){t=n(m,"OPTION",{});var p=c(t);a=v(p,l),o=k(p),p.forEach(r),this.h()},h(){t.__value=h=d[20].value,t.value=t.__value},m(m,p){C(m,t,p),e(t,a),e(t,o)},p:Se,d(m){m&&r(t)}}}function zt(d){let t,l=d[17].text+"",a,o,h;return{c(){t=s("option"),a=_(l),o=b(),this.h()},l(m){t=n(m,"OPTION",{});var p=c(t);a=v(p,l),o=k(p),p.forEach(r),this.h()},h(){t.__value=h=d[17].value,t.value=t.__value},m(m,p){C(m,t,p),e(t,a),e(t,o)},p:Se,d(m){m&&r(t)}}}function Wt(d){let t,l;return{c(){t=s("p"),l=_("Please fill out form completely")},l(a){t=n(a,"P",{});var o=c(t);l=v(o,"Please fill out form completely"),o.forEach(r)},m(a,o){C(a,t,o),e(t,l)},d(a){a&&r(t)}}}function xt(d){let t;function l(h,m){return h[7]?sa:h[5]?ra:oa}let a=l(d),o=a(d);return{c(){o.c(),t=Vt()},l(h){o.l(h),t=Vt()},m(h,m){o.m(h,m),C(h,t,m)},p(h,m){a===(a=l(h))&&o?o.p(h,m):(o.d(1),o=a(h),o&&(o.c(),o.m(t.parentNode,t)))},d(h){o.d(h),h&&r(t)}}}function oa(d){let t,l;return{c(){t=s("p"),l=_("Fetching infos ...")},l(a){t=n(a,"P",{});var o=c(t);l=v(o,"Fetching infos ..."),o.forEach(r)},m(a,o){C(a,t,o),e(t,l)},p:Se,d(a){a&&r(t)}}}function ra(d){let t,l=d[5].personalInfo.prettyText+"",a,o,h,m,p=d[5].officialInfo.lastDataUpdate+"",B;return{c(){t=s("p"),a=_(l),o=b(),h=s("p"),m=_("Data last fetched from deckbot sheet: "),B=_(p),this.h()},l(g){t=n(g,"P",{});var y=c(t);a=v(y,l),y.forEach(r),o=k(g),h=n(g,"P",{class:!0});var G=c(h);m=v(G,"Data last fetched from deckbot sheet: "),B=v(G,p),G.forEach(r),this.h()},h(){f(h,"class","text-xs")},m(g,y){C(g,t,y),e(t,a),C(g,o,y),C(g,h,y),e(h,m),e(h,B)},p(g,y){y&32&&l!==(l=g[5].personalInfo.prettyText+"")&&mt(a,l),y&32&&p!==(p=g[5].officialInfo.lastDataUpdate+"")&&mt(B,p)},d(g){g&&r(t),g&&r(o),g&&r(h)}}}function sa(d){let t,l;return{c(){t=s("p"),l=_(d[7])},l(a){t=n(a,"P",{});var o=c(t);l=v(o,d[7]),o.forEach(r)},m(a,o){C(a,t,o),e(t,l)},p(a,o){o&128&&mt(l,a[7])},d(a){a&&r(t)}}}function na(d){let t,l,a,o,h,m,p,B,g,y,G,te,z,fe,ae,W,ue,me,E,D,M,pe,x,T,Ne,q,le,Re,Be,U,Oe,K,Q,He,X,Ge,Ve,O,je,oe,Z,Ce,qe,F,V,Ke,re,Fe,Je,N,_e,Ye,ze,De,We,R,ve,xe,Qe,H,ge,Xe,Ze,be,$e,et,ke,tt,at,ye,lt,ot,Ee,rt,st,we,se,nt,it,ne,ie,ct,$,dt,ht,pt,ce=d[8],w=[];for(let u=0;u<ce.length;u+=1)w[u]=Yt(Jt(d,ce,u));let de=d[9],I=[];for(let u=0;u<de.length;u+=1)I[u]=zt(Ft(d,de,u));let L=d[6]&&Wt(),S=d[4]&&xt(d);return{c(){t=s("div"),l=s("div"),a=s("article"),o=s("h1"),h=_("How long to get my Steam Deck?"),m=b(),p=s("p"),B=_("Inspired from the reddit Steam Deck Order Email Megathreads "),g=s("a"),y=_("(list of them here)"),G=_(` 
        I wanted to give an alternative way of getting actual information
        besides talking to the deckbot within the reddit.`),te=b(),z=s("p"),fe=_(`Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
        and making the data available. 
        This site also relies on the data the people share in the reddit to 
        collect information which people are able to
        order their steam deck based on the reservation time, their region and their version.`),ae=b(),W=s("p"),ue=_(`Here you just need to enter your data to get your information about how far 
        away you might be from ordering your steam deck. Every week 
        you can just come back and see if something has changed (without a reddit 
        account and talking to deckbot every week).`),me=b(),E=s("form"),D=s("label"),M=s("span"),pe=_("In which region did you preorder your Steam Deck?"),x=b(),T=s("select");for(let u=0;u<w.length;u+=1)w[u].c();Ne=b(),q=s("label"),le=s("span"),Re=_("Which version did you reserve?"),Be=b(),U=s("select");for(let u=0;u<I.length;u+=1)I[u].c();Oe=b(),K=s("label"),Q=s("span"),He=_(`Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the DeckBot description `),X=s("a"),Ge=_("here"),Ve=b(),O=s("input"),je=b(),oe=s("label"),Z=s("button"),Ce=_("Get my current preorder status"),qe=b(),F=s("label"),V=s("input"),Ke=b(),re=s("span"),Fe=_("Remember me"),Je=b(),N=s("div"),_e=s("h3"),Ye=_("Results:"),ze=b(),L&&L.c(),De=b(),S&&S.c(),We=b(),R=s("div"),ve=s("h4"),xe=_("Changelog:"),Qe=b(),H=s("ul"),ge=s("li"),Xe=_("May 30, 2022: Updated to auto fetch data from deckbot google sheet"),Ze=b(),be=s("li"),$e=_("May 30, 2022: Updated data from deckbot"),et=b(),ke=s("li"),tt=_("May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response"),at=b(),ye=s("li"),lt=_("May 26, 2022: Updated data from deckbot googlesheet"),ot=b(),Ee=s("h4"),rt=_("Todo:"),st=b(),we=s("ul"),se=s("li"),nt=_("Auto update data from deckbot datasource"),it=b(),ne=s("div"),ie=s("p"),ct=_("If you have questions or comments feel free to reach out for me on reddit "),$=s("a"),dt=_("Labidou51"),this.h()},l(u){t=n(u,"DIV",{class:!0});var A=c(t);l=n(A,"DIV",{class:!0});var i=c(l);a=n(i,"ARTICLE",{class:!0});var P=c(a);o=n(P,"H1",{class:!0});var _t=c(o);h=v(_t,"How long to get my Steam Deck?"),_t.forEach(r),m=k(P),p=n(P,"P",{});var Le=c(p);B=v(Le,"Inspired from the reddit Steam Deck Order Email Megathreads "),g=n(Le,"A",{target:!0,href:!0});var vt=c(g);y=v(vt,"(list of them here)"),vt.forEach(r),G=v(Le,` 
        I wanted to give an alternative way of getting actual information
        besides talking to the deckbot within the reddit.`),Le.forEach(r),te=k(P),z=n(P,"P",{});var gt=c(z);fe=v(gt,`Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
        and making the data available. 
        This site also relies on the data the people share in the reddit to 
        collect information which people are able to
        order their steam deck based on the reservation time, their region and their version.`),gt.forEach(r),ae=k(P),W=n(P,"P",{});var bt=c(W);ue=v(bt,`Here you just need to enter your data to get your information about how far 
        away you might be from ordering your steam deck. Every week 
        you can just come back and see if something has changed (without a reddit 
        account and talking to deckbot every week).`),bt.forEach(r),P.forEach(r),me=k(i),E=n(i,"FORM",{});var j=c(E);D=n(j,"LABEL",{class:!0});var Ae=c(D);M=n(Ae,"SPAN",{class:!0});var kt=c(M);pe=v(kt,"In which region did you preorder your Steam Deck?"),kt.forEach(r),x=k(Ae),T=n(Ae,"SELECT",{class:!0,id:!0,name:!0});var yt=c(T);for(let ee=0;ee<w.length;ee+=1)w[ee].l(yt);yt.forEach(r),Ae.forEach(r),Ne=k(j),q=n(j,"LABEL",{class:!0});var Pe=c(q);le=n(Pe,"SPAN",{class:!0});var Et=c(le);Re=v(Et,"Which version did you reserve?"),Et.forEach(r),Be=k(Pe),U=n(Pe,"SELECT",{class:!0,id:!0,name:!0});var wt=c(U);for(let ee=0;ee<I.length;ee+=1)I[ee].l(wt);wt.forEach(r),Pe.forEach(r),Oe=k(j),K=n(j,"LABEL",{class:!0});var Te=c(K);Q=n(Te,"SPAN",{class:!0});var ft=c(Q);He=v(ft,`Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the DeckBot description `),X=n(ft,"A",{target:!0,href:!0});var It=c(X);Ge=v(It,"here"),It.forEach(r),ft.forEach(r),Ve=k(Te),O=n(Te,"INPUT",{type:!0,class:!0,name:!0,id:!0}),Te.forEach(r),je=k(j),oe=n(j,"LABEL",{class:!0});var St=c(oe);Z=n(St,"BUTTON",{class:!0,type:!0});var Dt=c(Z);Ce=v(Dt,"Get my current preorder status"),Dt.forEach(r),St.forEach(r),qe=k(j),F=n(j,"LABEL",{class:!0});var Ue=c(F);V=n(Ue,"INPUT",{type:!0,class:!0,name:!0,id:!0}),Ke=k(Ue),re=n(Ue,"SPAN",{class:!0});var Lt=c(re);Fe=v(Lt,"Remember me"),Lt.forEach(r),Ue.forEach(r),j.forEach(r),Je=k(i),N=n(i,"DIV",{class:!0});var he=c(N);_e=n(he,"H3",{});var At=c(_e);Ye=v(At,"Results:"),At.forEach(r),ze=k(he),L&&L.l(he),De=k(he),S&&S.l(he),he.forEach(r),We=k(i),R=n(i,"DIV",{class:!0});var J=c(R);ve=n(J,"H4",{});var Pt=c(ve);xe=v(Pt,"Changelog:"),Pt.forEach(r),Qe=k(J),H=n(J,"UL",{});var Y=c(H);ge=n(Y,"LI",{});var Tt=c(ge);Xe=v(Tt,"May 30, 2022: Updated to auto fetch data from deckbot google sheet"),Tt.forEach(r),Ze=k(Y),be=n(Y,"LI",{});var Ut=c(be);$e=v(Ut,"May 30, 2022: Updated data from deckbot"),Ut.forEach(r),et=k(Y),ke=n(Y,"LI",{});var Mt=c(ke);tt=v(Mt,"May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response"),Mt.forEach(r),at=k(Y),ye=n(Y,"LI",{});var Nt=c(ye);lt=v(Nt,"May 26, 2022: Updated data from deckbot googlesheet"),Nt.forEach(r),Y.forEach(r),ot=k(J),Ee=n(J,"H4",{});var Rt=c(Ee);rt=v(Rt,"Todo:"),Rt.forEach(r),st=k(J),we=n(J,"UL",{});var Bt=c(we);se=n(Bt,"LI",{class:!0});var Ot=c(se);nt=v(Ot,"Auto update data from deckbot datasource"),Ot.forEach(r),Bt.forEach(r),J.forEach(r),it=k(i),ne=n(i,"DIV",{class:!0});var Ht=c(ne);ie=n(Ht,"P",{});var ut=c(ie);ct=v(ut,"If you have questions or comments feel free to reach out for me on reddit "),$=n(ut,"A",{target:!0,href:!0});var Gt=c($);dt=v(Gt,"Labidou51"),Gt.forEach(r),ut.forEach(r),Ht.forEach(r),i.forEach(r),A.forEach(r),this.h()},h(){f(o,"class","text-center"),f(g,"target","_blank"),f(g,"href","https://www.reddit.com/r/SteamDeck/search?q=flair_name%3A%22MEGATHREAD%22&restrict_sr=1"),f(a,"class",""),f(M,"class","text-gray-700"),f(T,"class","form-select block rounded-md shadow-sm w-full mt-1"),f(T,"id","region"),f(T,"name","region"),d[1]===void 0&&jt(()=>d[11].call(T)),f(D,"class",""),f(le,"class","text-gray-700"),f(U,"class","form-select block rounded-md shadow-sm w-full mt-1"),f(U,"id","version"),f(U,"name","version"),d[2]===void 0&&jt(()=>d[12].call(U)),f(q,"class",""),f(X,"target","_blank"),f(X,"href","https://www.reddit.com/r/SteamDeck/comments/ui642q/introducing_deckbot/"),f(Q,"class","text-gray-700"),f(O,"type","number"),f(O,"class","form-input block rounded-md shadow-sm w-full mt-1"),f(O,"name","reservationTime"),f(O,"id","reserationTime"),f(K,"class",""),f(Z,"class","px-4 py-2 mt-5 font-semibold text-sm bg-sky-300 active:bg-sky-500 text-white rounded-lg shadow-sm"),f(Z,"type","submit"),f(oe,"class",""),f(V,"type","checkbox"),f(V,"class","form-input rounded-md shadow-sm"),f(V,"name","rememberme"),f(V,"id","rememberme"),f(re,"class","text-gray-700"),f(F,"class","block mt-3"),f(N,"class","block border-t-2"),f(se,"class","line-through"),f(R,"class","block border-t-2 text-xs"),f($,"target","_blank"),f($,"href","https://www.reddit.com/u/Labidou51"),f(ne,"class","block border-t-2"),f(l,"class","grid grid-cols-1 gap-6 content-center"),f(t,"class","container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose")},m(u,A){C(u,t,A),e(t,l),e(l,a),e(a,o),e(o,h),e(a,m),e(a,p),e(p,B),e(p,g),e(g,y),e(p,G),e(a,te),e(a,z),e(z,fe),e(a,ae),e(a,W),e(W,ue),e(l,me),e(l,E),e(E,D),e(D,M),e(M,pe),e(D,x),e(D,T);for(let i=0;i<w.length;i+=1)w[i].m(T,null);Me(T,d[1]),e(E,Ne),e(E,q),e(q,le),e(le,Re),e(q,Be),e(q,U);for(let i=0;i<I.length;i+=1)I[i].m(U,null);Me(U,d[2]),e(E,Oe),e(E,K),e(K,Q),e(Q,He),e(Q,X),e(X,Ge),e(K,Ve),e(K,O),Ct(O,d[0]),e(E,je),e(E,oe),e(oe,Z),e(Z,Ce),e(E,qe),e(E,F),e(F,V),V.checked=d[3],e(F,Ke),e(F,re),e(re,Fe),e(l,Je),e(l,N),e(N,_e),e(_e,Ye),e(N,ze),L&&L.m(N,null),e(N,De),S&&S.m(N,null),e(l,We),e(l,R),e(R,ve),e(ve,xe),e(R,Qe),e(R,H),e(H,ge),e(ge,Xe),e(H,Ze),e(H,be),e(be,$e),e(H,et),e(H,ke),e(ke,tt),e(H,at),e(H,ye),e(ye,lt),e(R,ot),e(R,Ee),e(Ee,rt),e(R,st),e(R,we),e(we,se),e(se,nt),e(l,it),e(l,ne),e(ne,ie),e(ie,ct),e(ie,$),e($,dt),ht||(pt=[Ie(T,"change",d[11]),Ie(U,"change",d[12]),Ie(O,"input",d[13]),Ie(V,"change",d[14]),Ie(E,"submit",ta(d[10]))],ht=!0)},p(u,[A]){if(A&256){ce=u[8];let i;for(i=0;i<ce.length;i+=1){const P=Jt(u,ce,i);w[i]?w[i].p(P,A):(w[i]=Yt(P),w[i].c(),w[i].m(T,null))}for(;i<w.length;i+=1)w[i].d(1);w.length=ce.length}if(A&258&&Me(T,u[1]),A&512){de=u[9];let i;for(i=0;i<de.length;i+=1){const P=Ft(u,de,i);I[i]?I[i].p(P,A):(I[i]=zt(P),I[i].c(),I[i].m(U,null))}for(;i<I.length;i+=1)I[i].d(1);I.length=de.length}A&516&&Me(U,u[2]),A&1&&Xt(O.value)!==u[0]&&Ct(O,u[0]),A&8&&(V.checked=u[3]),u[6]?L||(L=Wt(),L.c(),L.m(N,De)):L&&(L.d(1),L=null),u[4]?S?S.p(u,A):(S=xt(u),S.c(),S.m(N,null)):S&&(S.d(1),S=null)},i:Se,o:Se,d(u){u&&r(t),qt(w,u),qt(I,u),L&&L.d(),S&&S.d(),ht=!1,aa(pt)}}}const Qt="urn:getmydeck:rememberme";function ia(d,t,l){let a,o,h,m=!1,p=!1,B,g=!1,y,G=[{id:0,text:"",value:void 0},{id:1,text:"US",value:"US"},{id:2,text:"UK",value:"UK"},{id:3,text:"EU",value:"EU"}],te=[{id:0,text:"",value:void 0},{id:1,text:"64GB",value:"64"},{id:2,text:"256GB",value:"256"},{id:3,text:"512GB",value:"512"}];function z(){o!==void 0&&h!==void 0&&a!=null?(ae(o,h,a),l(6,g=!1),l(4,p=!0),console.log(m),m===!0?fe():localStorage.clear()):(l(6,g=!0),l(4,p=!1))}let fe=()=>{let D={region:o,version:h,timestamp:a};localStorage.setItem(Qt,JSON.stringify(D))},ae=async(D,M,pe)=>{l(7,y=""),await fetch(`/api/v2/regions/${D}/versions/${M}/infos/${pe}`).then(x=>x.json()).then(x=>{l(5,B=x)}).catch(()=>{l(7,y="Problem loading infos. Please fix your inputs.")}).finally(()=>{l(4,p=!0)})};la(async()=>{let D=localStorage.getItem(Qt);if(D!==null){let M=JSON.parse(D);l(0,a=M.timestamp),l(1,o=M.region),l(2,h=M.version),l(3,m=!0),ae(o,h,a)}});function W(){o=Kt(this),l(1,o),l(8,G)}function ue(){h=Kt(this),l(2,h),l(9,te)}function me(){a=Xt(this.value),l(0,a)}function E(){m=this.checked,l(3,m)}return[a,o,h,m,p,B,g,y,G,te,z,W,ue,me,E]}class da extends Zt{constructor(t){super(),$t(this,t,ia,na,ea,{})}}export{da as default};