import{S as Ne,i as Ue,s as Ae,e as h,c as d,a as m,d as u,g as K,n as G,w as Ve,t as L,h as P,b as _,J as i,j as Ge,k as D,m as I,K as we,L as Z,M as Te,N as $,O as Ce,P as Oe,p as ue,q as Re,r as fe,Q as Se,R as He,o as Ke,T as De,x as Me,y as je,z as qe,C as Ye}from"../chunks/index-a7ad466d.js";function ze(n){let e,s;return{c(){e=h("p"),s=L("loading..."),this.h()},l(t){e=d(t,"P",{class:!0});var l=m(e);s=P(l,"loading..."),l.forEach(u),this.h()},h(){_(e,"class","prose")},m(t,l){K(t,e,l),i(e,s)},p:G,d(t){t&&u(e)}}}function Fe(n){let e,s=n[0].personalInfo.prettyText+"",t;return{c(){e=h("p"),t=L(s),this.h()},l(l){e=d(l,"P",{class:!0});var a=m(e);t=P(a,s),a.forEach(u),this.h()},h(){_(e,"class","prose")},m(l,a){K(l,e,a),i(e,t)},p(l,a){a&1&&s!==(s=l[0].personalInfo.prettyText+"")&&Ge(t,s)},d(l){l&&u(e)}}}function Je(n){let e;function s(a,c){return a[0]?Fe:ze}let t=s(n),l=t(n);return{c(){e=h("div"),l.c()},l(a){e=d(a,"DIV",{});var c=m(e);l.l(c),c.forEach(u)},m(a,c){K(a,e,c),l.m(e,null)},p(a,[c]){t===(t=s(a))&&l?l.p(a,c):(l.d(1),l=t(a),l&&(l.c(),l.m(e,null)))},i:G,o:G,d(a){a&&u(e),l.d()}}}function Qe(n,e,s){let{region:t}=e,{version:l}=e,{reservationTime:a}=e,c;return Ve(async()=>{await fetch(`/api/v2/regions/${t}/versions/${l}/infos/${a}`).then(r=>r.json()).then(r=>{s(0,c=r)})}),n.$$set=r=>{"region"in r&&s(1,t=r.region),"version"in r&&s(2,l=r.version),"reservationTime"in r&&s(3,a=r.reservationTime)},[c,t,l,a]}class xe extends Ne{constructor(e){super(),Ue(this,e,Qe,Je,Ae,{region:1,version:2,reservationTime:3})}}function Ie(n,e,s){const t=n.slice();return t[10]=e[s],t}function Le(n,e,s){const t=n.slice();return t[13]=e[s],t}function Pe(n){let e,s=n[13].text+"",t,l,a;return{c(){e=h("option"),t=L(s),l=D(),this.h()},l(c){e=d(c,"OPTION",{});var r=m(e);t=P(r,s),l=I(r),r.forEach(u),this.h()},h(){e.__value=a=n[13],e.value=e.__value},m(c,r){K(c,e,r),i(e,t),i(e,l)},p:G,d(c){c&&u(e)}}}function Be(n){let e,s=n[10].text+"",t,l,a;return{c(){e=h("option"),t=L(s),l=D(),this.h()},l(c){e=d(c,"OPTION",{});var r=m(e);t=P(r,s),l=I(r),r.forEach(u),this.h()},h(){e.__value=a=n[10],e.value=e.__value},m(c,r){K(c,e,r),i(e,t),i(e,l)},p:G,d(c){c&&u(e)}}}function We(n){let e,s;return{c(){e=h("p"),s=L("input not complete..."),this.h()},l(t){e=d(t,"P",{class:!0});var l=m(e);s=P(l,"input not complete..."),l.forEach(u),this.h()},h(){_(e,"class","loading")},m(t,l){K(t,e,l),i(e,s)},p:G,i:G,o:G,d(t){t&&u(e)}}}function Xe(n){let e,s;return e=new xe({props:{region:n[1].value,version:n[2].value,reservationTime:n[0]}}),{c(){Me(e.$$.fragment)},l(t){je(e.$$.fragment,t)},m(t,l){qe(e,t,l),s=!0},p(t,l){const a={};l&2&&(a.region=t[1].value),l&4&&(a.version=t[2].value),l&1&&(a.reservationTime=t[0]),e.$set(a)},i(t){s||(fe(e.$$.fragment,t),s=!0)},o(t){ue(e.$$.fragment,t),s=!1},d(t){Ye(e,t)}}}function Ze(n){let e,s,t,l,a,c,r,w,B,F,J,k,ee,N,M,te,le,T,se,U,j,ne,oe,S,ae,q,C,re,ie,R,E,y,Q,ce,he,Y=n[4],v=[];for(let o=0;o<Y.length;o+=1)v[o]=Pe(Le(n,Y,o));let z=n[5],g=[];for(let o=0;o<z.length;o+=1)g[o]=Be(Ie(n,z,o));const de=[Xe,We],A=[];function _e(o,b){return o[3]?0:1}return E=_e(n),y=A[E]=de[E](n),{c(){e=h("div"),s=h("div"),t=h("div"),l=h("h1"),a=L("How long to get my Steam Deck"),c=D(),r=h("form"),w=h("label"),B=h("span"),F=L("In which region did you preorder your Steam Deck?"),J=D(),k=h("select");for(let o=0;o<v.length;o+=1)v[o].c();ee=D(),N=h("label"),M=h("span"),te=L("In which region did you preorder your Steam Deck?"),le=D(),T=h("select");for(let o=0;o<g.length;o+=1)g[o].c();se=D(),U=h("label"),j=h("span"),ne=L("Your reservation time"),oe=D(),S=h("input"),ae=D(),q=h("label"),C=h("button"),re=L("Get my current preorder status"),ie=D(),R=h("div"),y.c(),this.h()},l(o){e=d(o,"DIV",{class:!0});var b=m(e);s=d(b,"DIV",{class:!0});var p=m(s);t=d(p,"DIV",{class:!0});var f=m(t);l=d(f,"H1",{class:!0});var O=m(l);a=P(O,"How long to get my Steam Deck"),O.forEach(u),f.forEach(u),c=I(p),r=d(p,"FORM",{});var V=m(r);w=d(V,"LABEL",{class:!0});var x=m(w);B=d(x,"SPAN",{class:!0});var me=m(B);F=P(me,"In which region did you preorder your Steam Deck?"),me.forEach(u),J=I(x),k=d(x,"SELECT",{class:!0});var pe=m(k);for(let H=0;H<v.length;H+=1)v[H].l(pe);pe.forEach(u),x.forEach(u),ee=I(V),N=d(V,"LABEL",{class:!0});var W=m(N);M=d(W,"SPAN",{class:!0});var ve=m(M);te=P(ve,"In which region did you preorder your Steam Deck?"),ve.forEach(u),le=I(W),T=d(W,"SELECT",{class:!0});var ge=m(T);for(let H=0;H<g.length;H+=1)g[H].l(ge);ge.forEach(u),W.forEach(u),se=I(V),U=d(V,"LABEL",{class:!0});var X=m(U);j=d(X,"SPAN",{class:!0});var be=m(j);ne=P(be,"Your reservation time"),be.forEach(u),oe=I(X),S=d(X,"INPUT",{type:!0,class:!0,name:!0}),X.forEach(u),ae=I(V),q=d(V,"LABEL",{class:!0});var ke=m(q);C=d(ke,"BUTTON",{class:!0,type:!0});var Ee=m(C);re=P(Ee,"Get my current preorder status"),Ee.forEach(u),ke.forEach(u),V.forEach(u),ie=I(p),R=d(p,"DIV",{class:!0});var ye=m(R);y.l(ye),ye.forEach(u),p.forEach(u),b.forEach(u),this.h()},h(){_(l,"class","prose prose-lg text-center"),_(t,"class","block"),_(B,"class","text-gray-700"),_(k,"class","form-select block rounded-md shadow-sm w-full mt-1"),n[1]===void 0&&we(()=>n[7].call(k)),_(w,"class","block"),_(M,"class","text-gray-700"),_(T,"class","form-select block rounded-md shadow-sm w-full mt-1"),n[2]===void 0&&we(()=>n[8].call(T)),_(N,"class","block"),_(j,"class","text-gray-700"),_(S,"type","number"),_(S,"class","form-input block rounded-md shadow-sm w-full mt-1"),_(S,"name","reservationTime"),_(U,"class","block"),_(C,"class","px-4 py-2 mt-5 font-semibold text-sm bg-sky-300 text-white rounded-lg shadow-sm"),_(C,"type","submit"),_(q,"class","block"),_(R,"class","block mt-3 pt-3 border-t-2"),_(s,"class","grid grid-cols-1 gap-6 content-center"),_(e,"class","container mx-auto shadow-md p-5 mt-3 w-1/2 bg-white")},m(o,b){K(o,e,b),i(e,s),i(s,t),i(t,l),i(l,a),i(s,c),i(s,r),i(r,w),i(w,B),i(B,F),i(w,J),i(w,k);for(let p=0;p<v.length;p+=1)v[p].m(k,null);Z(k,n[1]),i(r,ee),i(r,N),i(N,M),i(M,te),i(N,le),i(N,T);for(let p=0;p<g.length;p+=1)g[p].m(T,null);Z(T,n[2]),i(r,se),i(r,U),i(U,j),i(j,ne),i(U,oe),i(U,S),Te(S,n[0]),i(r,ae),i(r,q),i(q,C),i(C,re),i(s,ie),i(s,R),A[E].m(R,null),Q=!0,ce||(he=[$(k,"change",n[7]),$(T,"change",n[8]),$(S,"input",n[9]),$(r,"submit",Ce(n[6]))],ce=!0)},p(o,[b]){if(b&16){Y=o[4];let f;for(f=0;f<Y.length;f+=1){const O=Le(o,Y,f);v[f]?v[f].p(O,b):(v[f]=Pe(O),v[f].c(),v[f].m(k,null))}for(;f<v.length;f+=1)v[f].d(1);v.length=Y.length}if(b&18&&Z(k,o[1]),b&32){z=o[5];let f;for(f=0;f<z.length;f+=1){const O=Ie(o,z,f);g[f]?g[f].p(O,b):(g[f]=Be(O),g[f].c(),g[f].m(T,null))}for(;f<g.length;f+=1)g[f].d(1);g.length=z.length}b&36&&Z(T,o[2]),b&1&&Oe(S.value)!==o[0]&&Te(S,o[0]);let p=E;E=_e(o),E===p?A[E].p(o,b):(Ke(),ue(A[p],1,1,()=>{A[p]=null}),Re(),y=A[E],y?y.p(o,b):(y=A[E]=de[E](o),y.c()),fe(y,1),y.m(R,null))},i(o){Q||(fe(y),Q=!0)},o(o){ue(y),Q=!1},d(o){o&&u(e),Se(v,o),Se(g,o),A[E].d(),ce=!1,He(he)}}}function $e(n,e,s){let t,l,a,c=!1,r=[{id:0,text:"",value:void 0},{id:1,text:"US",value:"US"},{id:2,text:"UK",value:"UK"},{id:3,text:"EU",value:"EU"}],w=[{id:0,text:"",value:void 0},{id:1,text:"64GB",value:"64"},{id:2,text:"256GB",value:"256"},{id:3,text:"512GB",value:"512"}];function B(){s(3,c=!0)}function F(){l=De(this),s(1,l),s(4,r)}function J(){a=De(this),s(2,a),s(5,w)}function k(){t=Oe(this.value),s(0,t)}return[t,l,a,c,r,w,B,F,J,k]}class tt extends Ne{constructor(e){super(),Ue(this,e,$e,Ze,Ae,{})}}export{tt as default};
