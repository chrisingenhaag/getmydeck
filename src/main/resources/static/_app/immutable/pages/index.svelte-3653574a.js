import{S as Be,i as Ue,s as Ae,e as h,t as T,k as I,c as _,a as p,h as L,m as D,d as c,g as G,J as n,n as z,l as be,b as d,K as ge,L as te,M as ke,N as le,O as Oe,P as Le,Q as Ee,R as Ve,j as Ne,T as ye}from"../chunks/index-a7ad466d.js";function we(o,e,l){const t=o.slice();return t[14]=e[l],t}function Se(o,e,l){const t=o.slice();return t[17]=e[l],t}function Pe(o){let e,l=o[17].text+"",t,s,i;return{c(){e=h("option"),t=T(l),s=I(),this.h()},l(f){e=_(f,"OPTION",{});var u=p(e);t=L(u,l),s=D(u),u.forEach(c),this.h()},h(){e.__value=i=o[17],e.value=e.__value},m(f,u){G(f,e,u),n(e,t),n(e,s)},p:z,d(f){f&&c(e)}}}function Ie(o){let e,l=o[14].text+"",t,s,i;return{c(){e=h("option"),t=T(l),s=I(),this.h()},l(f){e=_(f,"OPTION",{});var u=p(e);t=L(u,l),s=D(u),u.forEach(c),this.h()},h(){e.__value=i=o[14],e.value=e.__value},m(f,u){G(f,e,u),n(e,t),n(e,s)},p:z,d(f){f&&c(e)}}}function De(o){let e,l;return{c(){e=h("p"),l=T("Please fill out form completely")},l(t){e=_(t,"P",{});var s=p(e);l=L(s,"Please fill out form completely"),s.forEach(c)},m(t,s){G(t,e,s),n(e,l)},d(t){t&&c(e)}}}function Te(o){let e;function l(i,f){return i[6]?Ce:i[4]?Re:Ge}let t=l(o),s=t(o);return{c(){s.c(),e=be()},l(i){s.l(i),e=be()},m(i,f){s.m(i,f),G(i,e,f)},p(i,f){t===(t=l(i))&&s?s.p(i,f):(s.d(1),s=t(i),s&&(s.c(),s.m(e.parentNode,e)))},d(i){s.d(i),i&&c(e)}}}function Ge(o){let e,l;return{c(){e=h("p"),l=T("fetching infos ...")},l(t){e=_(t,"P",{});var s=p(e);l=L(s,"fetching infos ..."),s.forEach(c)},m(t,s){G(t,e,s),n(e,l)},p:z,d(t){t&&c(e)}}}function Re(o){let e,l=o[4].personalInfo.prettyText+"",t;return{c(){e=h("p"),t=T(l)},l(s){e=_(s,"P",{});var i=p(e);t=L(i,l),i.forEach(c)},m(s,i){G(s,e,i),n(e,t)},p(s,i){i&16&&l!==(l=s[4].personalInfo.prettyText+"")&&Ne(t,l)},d(s){s&&c(e)}}}function Ce(o){let e,l;return{c(){e=h("p"),l=T(o[6])},l(t){e=_(t,"P",{});var s=p(e);l=L(s,o[6]),s.forEach(c)},m(t,s){G(t,e,s),n(e,l)},p(t,s){s&64&&Ne(l,t[6])},d(t){t&&c(e)}}}function He(o){let e,l,t,s,i,f,u,w,N,H,K,g,F,B,A,J,Q,k,x,S,M,se,oe,P,ae,j,R,re,ne,U,W,ie,ce,Y=o[7],m=[];for(let r=0;r<Y.length;r+=1)m[r]=Pe(Se(o,Y,r));let q=o[8],v=[];for(let r=0;r<q.length;r+=1)v[r]=Ie(we(o,q,r));let E=o[5]&&De(),b=o[3]&&Te(o);return{c(){e=h("div"),l=h("div"),t=h("div"),s=h("h1"),i=T("How long to get my Steam Deck"),f=I(),u=h("form"),w=h("label"),N=h("span"),H=T("In which region did you preorder your Steam Deck?"),K=I(),g=h("select");for(let r=0;r<m.length;r+=1)m[r].c();F=I(),B=h("label"),A=h("span"),J=T("In which region did you preorder your Steam Deck?"),Q=I(),k=h("select");for(let r=0;r<v.length;r+=1)v[r].c();x=I(),S=h("label"),M=h("span"),se=T("Your reservation time (in seconds from 01.01.1970 example: 1627022437)"),oe=I(),P=h("input"),ae=I(),j=h("label"),R=h("button"),re=T("Get my current preorder status"),ne=I(),U=h("div"),E&&E.c(),W=I(),b&&b.c(),this.h()},l(r){e=_(r,"DIV",{class:!0});var y=p(e);l=_(y,"DIV",{class:!0});var a=p(l);t=_(a,"DIV",{class:!0});var O=p(t);s=_(O,"H1",{});var ue=p(s);i=L(ue,"How long to get my Steam Deck"),ue.forEach(c),O.forEach(c),f=D(a),u=_(a,"FORM",{});var V=p(u);w=_(V,"LABEL",{class:!0});var X=p(w);N=_(X,"SPAN",{class:!0});var fe=p(N);H=L(fe,"In which region did you preorder your Steam Deck?"),fe.forEach(c),K=D(X),g=_(X,"SELECT",{class:!0,id:!0,name:!0});var de=p(g);for(let C=0;C<m.length;C+=1)m[C].l(de);de.forEach(c),X.forEach(c),F=D(V),B=_(V,"LABEL",{class:!0});var Z=p(B);A=_(Z,"SPAN",{class:!0});var he=p(A);J=L(he,"In which region did you preorder your Steam Deck?"),he.forEach(c),Q=D(Z),k=_(Z,"SELECT",{class:!0,id:!0,name:!0});var _e=p(k);for(let C=0;C<v.length;C+=1)v[C].l(_e);_e.forEach(c),Z.forEach(c),x=D(V),S=_(V,"LABEL",{class:!0});var $=p(S);M=_($,"SPAN",{class:!0});var pe=p(M);se=L(pe,"Your reservation time (in seconds from 01.01.1970 example: 1627022437)"),pe.forEach(c),oe=D($),P=_($,"INPUT",{type:!0,class:!0,name:!0,id:!0}),$.forEach(c),ae=D(V),j=_(V,"LABEL",{class:!0});var me=p(j);R=_(me,"BUTTON",{class:!0,type:!0});var ve=p(R);re=L(ve,"Get my current preorder status"),ve.forEach(c),me.forEach(c),V.forEach(c),ne=D(a),U=_(a,"DIV",{class:!0});var ee=p(U);E&&E.l(ee),W=D(ee),b&&b.l(ee),ee.forEach(c),a.forEach(c),y.forEach(c),this.h()},h(){d(t,"class","block prose-lg text-center"),d(N,"class","text-gray-700"),d(g,"class","form-select block rounded-md shadow-sm w-full mt-1"),d(g,"id","region"),d(g,"name","region"),o[1]===void 0&&ge(()=>o[10].call(g)),d(w,"class","block"),d(A,"class","text-gray-700"),d(k,"class","form-select block rounded-md shadow-sm w-full mt-1"),d(k,"id","version"),d(k,"name","version"),o[2]===void 0&&ge(()=>o[11].call(k)),d(B,"class","block"),d(M,"class","text-gray-700"),d(P,"type","number"),d(P,"class","form-input block rounded-md shadow-sm w-full mt-1"),d(P,"name","reservationTime"),d(P,"id","reserationTime"),d(S,"class","block"),d(R,"class","px-4 py-2 mt-5 font-semibold text-sm bg-sky-300 active:bg-sky-500 text-white rounded-lg shadow-sm"),d(R,"type","submit"),d(j,"class","block"),d(U,"class","block mt-3 pt-3 border-t-2 prose-lg"),d(l,"class","grid grid-cols-1 gap-6 content-center"),d(e,"class","container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white")},m(r,y){G(r,e,y),n(e,l),n(l,t),n(t,s),n(s,i),n(l,f),n(l,u),n(u,w),n(w,N),n(N,H),n(w,K),n(w,g);for(let a=0;a<m.length;a+=1)m[a].m(g,null);te(g,o[1]),n(u,F),n(u,B),n(B,A),n(A,J),n(B,Q),n(B,k);for(let a=0;a<v.length;a+=1)v[a].m(k,null);te(k,o[2]),n(u,x),n(u,S),n(S,M),n(M,se),n(S,oe),n(S,P),ke(P,o[0]),n(u,ae),n(u,j),n(j,R),n(R,re),n(l,ne),n(l,U),E&&E.m(U,null),n(U,W),b&&b.m(U,null),ie||(ce=[le(g,"change",o[10]),le(k,"change",o[11]),le(P,"input",o[12]),le(u,"submit",Oe(o[9]))],ie=!0)},p(r,[y]){if(y&128){Y=r[7];let a;for(a=0;a<Y.length;a+=1){const O=Se(r,Y,a);m[a]?m[a].p(O,y):(m[a]=Pe(O),m[a].c(),m[a].m(g,null))}for(;a<m.length;a+=1)m[a].d(1);m.length=Y.length}if(y&130&&te(g,r[1]),y&256){q=r[8];let a;for(a=0;a<q.length;a+=1){const O=we(r,q,a);v[a]?v[a].p(O,y):(v[a]=Ie(O),v[a].c(),v[a].m(k,null))}for(;a<v.length;a+=1)v[a].d(1);v.length=q.length}y&260&&te(k,r[2]),y&1&&Le(P.value)!==r[0]&&ke(P,r[0]),r[5]?E||(E=De(),E.c(),E.m(U,W)):E&&(E.d(1),E=null),r[3]?b?b.p(r,y):(b=Te(r),b.c(),b.m(U,null)):b&&(b.d(1),b=null)},i:z,o:z,d(r){r&&c(e),Ee(m,r),Ee(v,r),E&&E.d(),b&&b.d(),ie=!1,Ve(ce)}}}function Ke(o,e,l){let t,s,i,f=!1,u,w=!1,N,H=[{id:0,text:"",value:void 0},{id:1,text:"US",value:"US"},{id:2,text:"UK",value:"UK"},{id:3,text:"EU",value:"EU"}],K=[{id:0,text:"",value:void 0},{id:1,text:"64GB",value:"64"},{id:2,text:"256GB",value:"256"},{id:3,text:"512GB",value:"512"}];function g(){s.value!==void 0&&i.value!==void 0&&t!=null?(F(s.value,i.value,t),l(5,w=!1),l(3,f=!0)):(l(5,w=!0),l(3,f=!1))}let F=async(Q,k,x)=>{l(6,N=void 0),await fetch(`/api/v2/regions/${Q}/versions/${k}/infos/${x}`).then(S=>S.json()).then(S=>{l(4,u=S),l(3,f=!0)}).catch(()=>{l(6,N="Problem loading infos. Please fix your inputs.")})};function B(){s=ye(this),l(1,s),l(7,H)}function A(){i=ye(this),l(2,i),l(8,K)}function J(){t=Le(this.value),l(0,t)}return[t,s,i,f,u,w,N,H,K,g,B,A,J]}class je extends Be{constructor(e){super(),Ue(this,e,Ke,He,Ae,{})}}export{je as default};