(self.webpackChunkkatalon_webview=self.webpackChunkkatalon_webview||[]).push([[680],{7436:function(e,t,n){"use strict";n.r(t),n.d(t,{default:function(){return _}});var a=n(2791),o=n(8596),r=n(9335),i=n.p+"static/media/Start-Tour-Web-Testing.f4ccbecaec1c67525384.png",s=n(184),c=(0,o.Z)((function(){return{root:{backgroundImage:"url(".concat(i,")"),position:"fixed",top:"0",left:"0",width:"100%",height:"100%",margin:"0",backgroundSize:"cover",backgroundPosition:"center",overflow:"auto",animation:"fadeIn 1s ease-in-out"}}})),u=function(e){var t=e.Content,n=c();return(0,s.jsx)("div",{className:n.root,children:(0,s.jsx)(r.ZP,{autoHide:!0,hideTracksWhenNotNeeded:!0,height:"100%",children:(0,s.jsx)(t,{})})})},l=n(4942),f=n(9439),d=n(1288),h=n(8302),m=n(6513),g=n(996),p=n(6016);var T=n.p+"static/media/congratulation.081f39c891f7e7971c9220f4e3d454f8.svg",Z=(0,o.Z)({root:{background:'#fff url("'.concat(i,'") center center/cover no-repeat'),height:"100%",marginTop:"0",animation:"fadeIn .5s ease-in-out"},title:{fontFamily:"Roboto",fontWeight:400,animation:"fadeIn 1s ease-in-out"},message:{animation:"fadeIn 1s ease-in-out",fontWeight:"300 !important",textTransform:"initial"}}),x=function(e){var t=e.title,n=e.message,a=e.onContinue,o=Z();return(0,s.jsxs)(d.Z,{container:!0,spacing:4,justify:"center",alignItems:"center",alignContent:"center",className:o.root,children:[(0,s.jsx)(d.Z,{item:!0,xs:10,md:12,children:(0,s.jsx)(h.Z,{align:"center",variant:"h4",className:o.title,children:t})}),(0,s.jsx)(d.Z,{item:!0,xs:6,md:4,children:(0,s.jsx)("img",{src:T,alt:"Congratulation!"})}),(0,s.jsx)(d.Z,{item:!0,xs:10,md:12,children:(0,s.jsx)(h.Z,{align:"center",variant:"h6",className:o.message,children:n})}),(0,s.jsx)(d.Z,{item:!0,xs:10,md:12,container:!0,justifyContent:"center",children:(0,s.jsx)(m.Z,{color:"primary",variant:"contained",disableElevation:!0,size:"large",onClick:a,style:{textTransform:"none"},children:"Show Me!"})})]})},v=n(2483),j=(0,o.Z)({root:{background:'#fff url("'.concat(i,'") center center/cover no-repeat'),height:"100%",marginTop:"0"},welcomeMessage:{fontFamily:"Roboto",fontWeight:300,animation:"fadeIn 1s ease-in-out"},skipButton:{textTransform:"initial",fontWeight:"normal"}}),y=function(){var e,t=j(),n=new URLSearchParams(window.location.search),o=n.get("prev_tour"),r=n.get("next_tour"),i=null!==(e=n.get("ignore_congratulation"))&&void 0!==e&&e,c=a.useState(!!o&&!i),u=(0,f.Z)(c,2),T=u[0],Z=u[1];var y=function(){var e,t,n,a=(e={},(0,l.Z)(e,p.Z.WEB_TESTING,"Congratulations! You have just learned how to create and verify a test case!"),(0,l.Z)(e,p.Z.API_TESTING,"Congratulations! You have just learned how to create and verify an API!"),(0,l.Z)(e,p.Z.BDD_TESTING_ADDING_TO_TEST_CASE,"Congratulations! You have just learned how to create and verify a BDD test case!"),(0,l.Z)(e,p.Z.TEST_PLANNING,"Congratulations! You have just learned how to plan your tests!"),e),i=(t={},(0,l.Z)(t,p.Z.WEB_TESTING,""),(0,l.Z)(t,p.Z.API_TESTING,""),(0,l.Z)(t,p.Z.TEST_PLANNING,""),(0,l.Z)(t,p.Z.WHAT_NEXT,""),t),s=(n={},(0,l.Z)(n,p.Z.WEB_TESTING,""),(0,l.Z)(n,p.Z.API_TESTING,""),(0,l.Z)(n,p.Z.TEST_PLANNING,""),(0,l.Z)(n,p.Z.WHAT_NEXT,""),n);return[a[o]||"Congratulations! You have just learned a Quickstart Tour!!",(i[o]||"Are you ready for the next step? ")+(s[r]||"Let\u2019s continue with our next tour!")]}(),_=(0,f.Z)(y,2),N=_[0],I=_[1];return T?(0,s.jsx)(x,{title:N,message:I,onContinue:function(){Z(!1)}}):(0,s.jsxs)(d.Z,{container:!0,spacing:4,justifyContent:"center",alignItems:"center",alignContent:"center",className:t.root,children:[(0,s.jsx)(d.Z,{item:!0,xs:10,sm:12,children:(0,s.jsx)(h.Z,{align:"center",variant:"h4",className:t.welcomeMessage,children:"Let's explore the power of Katalon Studio"})}),(0,s.jsx)(d.Z,{item:!0,xs:10,sm:10,children:(0,s.jsx)(v.s,{hideSkipButton:!0})}),(0,s.jsx)(d.Z,{item:!0,container:!0,xs:10,justify:"center",children:(0,s.jsx)(m.Z,{color:"primary",size:"small",onClick:function(){g.Z.startTour(p.Z.SKIP_TOUR)},className:t.skipButton,children:"Maybe Later"})})]})};function _(){return(0,s.jsx)(u,{Content:y})}},888:function(e,t,n){"use strict";var a=n(9047);function o(){}function r(){}r.resetWarningCache=o,e.exports=function(){function e(e,t,n,o,r,i){if(i!==a){var s=new Error("Calling PropTypes validators directly is not supported by the `prop-types` package. Use PropTypes.checkPropTypes() to call them. Read more at http://fb.me/use-check-prop-types");throw s.name="Invariant Violation",s}}function t(){return e}e.isRequired=e;var n={array:e,bigint:e,bool:e,func:e,number:e,object:e,string:e,symbol:e,any:e,arrayOf:t,element:e,elementType:e,instanceOf:t,node:e,objectOf:t,oneOf:t,oneOfType:t,shape:t,exact:t,checkPropTypes:r,resetWarningCache:o};return n.PropTypes=n,n}},2007:function(e,t,n){e.exports=n(888)()},9047:function(e){"use strict";e.exports="SECRET_DO_NOT_PASS_THIS_OR_YOU_WILL_BE_FIRED"}}]);
//# sourceMappingURL=680.8a31cfde.chunk.js.map