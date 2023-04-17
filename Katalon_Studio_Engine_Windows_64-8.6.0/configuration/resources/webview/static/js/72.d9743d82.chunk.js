"use strict";(self.webpackChunkkatalon_webview=self.webpackChunkkatalon_webview||[]).push([[72],{9072:function(e,t,n){n.r(t),n.d(t,{default:function(){return X}});var r=n(2791),a=n(158),i=n(1288),s=n(9335),c=n(4942),o=n(8596),A=(0,o.Z)((function(e){var t;return{root:(t={width:"100%",height:"100%",margin:0,flexWrap:"nowrap",minHeight:"500px"},(0,c.Z)(t,e.breakpoints.down("sm"),{flexWrap:"wrap",minHeight:"300px",height:"auto"}),(0,c.Z)(t,"& a",{textDecoration:"none",color:"#000000","&:hover":{color:"#072593"},"&:active":{color:"#001b56"}}),(0,c.Z)(t,"& > *",{}),t),mainContentWrapper:{minHeight:"100%",minWidth:"100%",height:"initial !important"},contentWrapperNoScroll:(0,c.Z)({minWidth:"100%"},e.breakpoints.up(960),{display:"none"}),contentWrapper:(0,c.Z)({minHeight:"100%",minWidth:"100%",height:"initial !important"},e.breakpoints.down(960),{display:"none"}),skeleton:{animation:"fadeIn 1s ease-in-out"},card:{borderRadius:"10px !important",display:"flex",flexDirection:"column",height:"100%",animation:"fadeIn 1.5s ease-in-out",boxShadow:"none"},halfCardContainer:(0,c.Z)({height:"calc(50% - 8px)","&:nth-child(2)":{marginTop:"16px"}},e.breakpoints.down("sm"),{height:"auto"}),cardHeader:{padding:"16px 16px 8px !important","& > div > span":{fontWeight:"bold !important",letterSpacing:".5px",color:"#8e8e8e"}},cardContent:{flex:"1 1 auto !important",padding:"0 16px 16px !important",overflow:"auto",display:"flex"}}})),u=n(1413),l=n(5987),h=n(4165),p=n(5861),d=n(9439),f=n(8749),x=n(6252),Z=n(4697),m=n(7025),g=n(4843),j=n(2656),v=n(996),w=n(390),k={BestPractices:"https://raw.githubusercontent.com/katalon-studio/katalon-studio/master/katalon-start-page/best-practices.md",ReleaseHighlights:"https://raw.githubusercontent.com/katalon-studio/katalon-studio/master/katalon-start-page/release-highlights.md",News:"https://raw.githubusercontent.com/katalon-studio/katalon-studio/master/katalon-start-page/marketing-content.md",ForumTopics:"https://forum.katalon.com/top/monthly.json"},N=n(9868),E=n(3408),C=n(3764),b=n(3264),B=n(184),I=(0,r.memo)((function(e){return(0,B.jsxs)("div",(0,u.Z)((0,u.Z)({},e),{},{children:[(0,B.jsx)(b.Z,{variant:"text",animation:"wave"}),(0,B.jsx)(b.Z,{variant:"text",width:"40%",animation:"wave"}),(0,B.jsx)(b.Z,{variant:"rect",width:"100%",height:"70px",animation:"wave",style:{marginTop:".5rem"}})]}))})),R=function(e){var t=e.content,n=e.area,r=e.components,a=A(),i=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(t,n){var r;return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r=t.target.href,e.next=3,E.Z.trackStartPage(n,r);case 3:case"end":return e.stop()}}),e)})));return function(t,n){return e.apply(this,arguments)}}();return t?(0,B.jsx)("div",{onClickCapture:function(e){return i(e,n)},children:(0,B.jsx)(C.D,{children:t,rehypePlugins:[N.Z],className:a.skeleton,components:r})}):(0,B.jsx)(I,{})},y=n(5950),S=n(758),T=n(6950),U=function(e){return(0,B.jsx)("img",{src:e,alt:"icon",style:{height:"1rem",verticalAlign:"text-top"}})};function W(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"",t=e.match(/\[.*?\]\((https?:\/\/(www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_+.~#?&//=]*))\)/i);return t&&t[1]}var D=function(e){var t=e.icon,n=e.title,r=A().cardHeader;return(0,B.jsx)(T.Z,{subheader:(0,B.jsxs)("div",{children:[U(t)," ",n]}),className:r})},P=["node"],H=["node"],K=function(e){var t=e.elevation,n=A(),a=(0,r.useState)(),c=(0,d.Z)(a,2),o=c[0],N=c[1],E=(0,r.useState)(),C=(0,d.Z)(E,2),b=C[0],I=C[1],T=r.useState({}),U=(0,d.Z)(T,2),W=U[0],K=U[1],Q=W&&W.bestPracticeReviews&&null!=W.bestPracticeReviews[b],Y=Q&&!0===W.bestPracticeReviews[b],F="lightgray",V=Q?Y?"#1646c7":F:"secondary",M=Q?Y?F:"#cd4a4a":"primary",G=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,w.Z.fetchContent(k.BestPractices).then((function(e){N(e),I()}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),O=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,w.Z.fetchUserProfile().then((function(e){K(e||{})}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),L=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,v.Z.reviewBestPractice(b,!0).then((function(){O()}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),q=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,v.Z.reviewBestPractice(b,!1).then((function(){O()}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return r.useEffect((function(){setTimeout((function(){G(),O()}),1e3)}),[]),(0,B.jsx)(B.Fragment,{children:(0,B.jsxs)(f.Z,{elevation:t,className:n.card,children:[(0,B.jsx)(D,{icon:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACIAAAAgCAYAAAB3j6rJAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAIqADAAQAAAABAAAAIAAAAAB4uvI0AAADO0lEQVRYCcVXTWjUUBCeSRZrCx5EwYN4ku2lvaj4U7TuZlcEqUpBqYceRFBED+KlaeuhFi9td28eFEEQDz20KBT8KYi72WrFqqg9d/FUPIqeWitJxnlbk6Zr8pJNKi6EN5mZ75sv817eywIk/PWVtYy4EtJAKikB44f+cOSTcGES8GBZO2wCzQqOFOCRkZzxJi6fEhcocBbSTQfvtR1fI2PsjujGsUNE5ltvMcRUR0F7Oef1RbVjd4TAdLuxVsx01suaK6IVqyMDlfx+y7be+9VQFfXAaLb0wS8m88XqiE1W4JPLYjIhDXek71VuL5j2RxkppJR9xaPlT9KcumDjHbEosBsud5QcN3nVCOzI4Lv8NmsJ04B2GsluJWCbIE1Ie3gMxNVoETgNP3NWlc0qobIApFTVFqqOHCx9q9OwChk2hlPL8PqszcUAiYtibSSCrX6ApD5E+M4yWRyxOKwqLLIZOh/VnqzfyF62Ae6GPmlSFfV4bhevjStjWuWe22K9krsANt2nWqwesfH3yHMOCl4sZMsPBLsrRNzoRraXCB8CkCru/90PLUQ6X9Aq406NdUKEs39G6yGbxnmNbMTJ7NRxR14jJirYO5YxJl0nG38JEUHdyHcD2RM8TZu8yUltno5fgMq5glaaqufyFSKS+staFy/gxzxNTfWgePe4wgvzzFjOeOaHDxQikgdmcsct257it6nZDxzZh7CsKkr3aKb8IggjFSJAfUb+JJD1JIggkh/VU0Wt9FSWG7rFK4A/ZARRYlE4QoUQ2O1RislyonBEEEKJhQDYbTKhIhYqhN+aUJKwIgQQ+jARhISThAnheDIhN+byO/jV3S4rxDvlrLhkOYKjxiVJknbEXJIsVIR5ULGLz4tOcQmb9+n5oFpSLgZJzxP+Zmjnp1n34216gf1DhawxiXxyOcFixnhORNN6Revhb5pbfDy0OjEx1rgASl6f15Z2hHe7tblFWORz4lILZtqKWmXCK8IhFD4REzkilzu06Ma8XI7TM0p3Vv4sEH+gdhPiyK6dm+9cS0+veLCh5u3qiabFrz+vItEgJ3/hKewIBfkl6Ebuuj57eotfrBGf4BBcjWD+W+5vuwcKbD78eE4AAAAASUVORK5CYII=",title:"BEST PRACTICES"}),(0,B.jsxs)(x.Z,{className:n.cardContent,children:[(0,B.jsx)(s.ZP,{autoHide:!0,hideTracksWhenNotNeeded:!0,className:n.contentWrapper,children:(0,B.jsx)(R,{content:o,area:y.Z.BestPractices,components:{img:function(e){e.node;var t=(0,l.Z)(e,P);return(0,B.jsx)("img",(0,u.Z)((0,u.Z)({},t),{},{style:{width:"calc(100%)"},alt:"markdown-img"}))}}})}),(0,B.jsx)("div",{className:n.contentWrapperNoScroll,children:(0,B.jsx)(R,{content:o,area:y.Z.BestPractices,components:{img:function(e){e.node;var t=(0,l.Z)(e,H);return(0,B.jsx)("img",(0,u.Z)((0,u.Z)({},t),{},{style:{width:"calc(100%)"},alt:"markdown-img"}))}}})})]}),(0,B.jsx)(Z.Z,{children:(0,B.jsxs)(i.Z,{container:!0,justifyContent:"space-between",alignItems:"center",spacing:2,children:[(0,B.jsxs)(i.Z,{item:!0,children:[(0,B.jsx)(S.Z,{area:y.Z.LikeBestPractices,asLink:!1,onClick:L,children:(0,B.jsx)(m.Z,{"aria-label":"Like",size:"small",children:(0,B.jsx)(g.Z,{color:"primary",style:{color:V}})})}),(0,B.jsx)(S.Z,{area:y.Z.UnlikeBestPractices,asLink:!1,onClick:q,children:(0,B.jsx)(m.Z,{"aria-label":"Dislike",size:"small",children:(0,B.jsx)(j.Z,{color:"secondary",style:{color:M}})})})]}),(0,B.jsx)(i.Z,{item:!0,children:(0,B.jsx)(S.Z,{area:y.Z.BestPractices,href:b,children:"Learn more"})})]})})]})})},Q=n(1632),Y=n(9806),F=function(e){var t=e.elevation,n=A(),a=r.useState(),c=(0,d.Z)(a,2),o=c[0],u=c[1],l=r.useState(),m=(0,d.Z)(l,2),g=m[0],j=m[1],N=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,w.Z.fetchContent(k.ReleaseHighlights).then((function(e){u(e),j(W(e))}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),E=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,v.Z.checkForUpdate();case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return r.useEffect((function(){setTimeout((function(){N()}),1e3)}),[]),(0,B.jsxs)(f.Z,{elevation:t,className:n.card,children:[(0,B.jsx)(D,{icon:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAHqADAAQAAAABAAAAHgAAAADKQTcFAAADV0lEQVRIDb1XXUhUQRQ+M/dezVYqCIyk0rCyQpLooUghV6SHQAiTIgiyH4iQCqlYHxeiHxEJIqiHDPGhCCxCeujBXJOMHirbQqjU0rSVNmIX1Hb92Z3mjN1157r37q1VB+7OnDPnfN/MnnNnziVgo7mZm4Y6XuxmEN3Pnx2EkWwgLFu4MuJjhPkI0Df8eZxRUvzSTdzRZLDEysDtqVoSYoNnOcp5AJZlZTs7R/wUoCGD5NxwO5vCs3p5ZEpc2+48ECHsOjBYK7vYlAgMKYzUXCv1PEzkwRcnN8YYcXU4L0eAtfw3KULyBSMGYiGmzAIgKWZIS+/z/pDRMBWZEPKgrqT9MO+ZjqPoA+zDzk5c3el43TyNC559a9a6mgbadbzYjkVM8e9dwKYAqdRjLogxe8dh4HNKMbWzYJ5wDsjdhNkukgtfmQUnxYXxhBNcfEjwcBj3PB+x/56ab40SBRiLcvxYDiUwJn6Hc89qZWeJWsRPozMJLP5JVZZ7FE4U1kHRmgoYGv0IgfAPM39HZHC4jeIxaGZhR08JhYObXbB3fRWoVIPMtBWwL++UpStyqnj2WlrxSYVo/JdBhE1LphpNhyMFbtiycpekn4yEJNkoIKeKB75VTLavKoPK/Asibk/6bsErX6vAWaoug2PbrkLO8q0S7thkAFp7b0o6o4CcqrhlLHKhfEM1aEq68K3IrwGNpsGHn51wsrAeshzrJMxfIR/c8V4E7C0bv9lUSwM+GZ4eF3HT7co3VgMmUoaWqatE/320Fxq9LhibCkh6E4FR4PepyaRQt3yqh6nIhGRiJO0LvIXb3efskvJ0ISMUL3EJ1SB8CXrh7vtaMEsYr98jdjqRJKHiYZGTYuUQr0w07g++mwGflrO1a/gR3Ou5NCfbE2EYdK+V4qq83zy9jxsm5ojBCT/0B7t5QuVAlEXg6ddGaBtonmNnR0GJ6prXI9MOKT+lxZFJsTDjN0WDPafUrZDrLycAFma8FhlKHTYJAucQXNyMLwAA70cszJK4pTyNHHrlKYgRESsDSsmVlNFNABBbrz7QJFb6oLCYxV5sx2IVvArEanA+d45YxgpTcOFPorboBb2+CIwHFmYUiAvfPV2fvMdPGOJC3/iYGv2kGBsndXkhPtr+ACZQaxXEO1/rAAAAAElFTkSuQmCC",title:"RELEASE HIGHLIGHTS"}),(0,B.jsxs)(x.Z,{className:n.cardContent,children:[(0,B.jsx)(s.ZP,{autoHide:!0,hideTracksWhenNotNeeded:!0,height:"100%",className:n.contentWrapper,children:(0,B.jsx)(R,{content:o,area:y.Z.ReleaseHighlights})}),(0,B.jsx)("div",{className:n.contentWrapperNoScroll,children:(0,B.jsx)(R,{content:o,area:y.Z.ReleaseHighlights})})]}),(0,B.jsx)(Z.Z,{children:(0,B.jsxs)(i.Z,{container:!0,justifyContent:"space-between",alignItems:"center",spacing:2,children:[(0,B.jsx)(i.Z,{item:!0,children:(0,B.jsxs)(S.Z,{onClick:E,area:y.Z.Upgrade,asButton:!0,children:["Update now\xa0",(0,B.jsx)(Y.G,{icon:Q.Vfw,style:{marginBottom:3}})]})}),(0,B.jsx)(i.Z,{item:!0,children:(0,B.jsx)(S.Z,{area:y.Z.ReleaseHighlights,href:g,children:"Learn more"})})]})})]})},V=n(2434),M=function(e){var t=e.area,n=e.href;return(0,B.jsx)(Z.Z,{children:(0,B.jsx)(i.Z,{container:!0,justifyContent:"flex-end",alignItems:"center",spacing:2,children:(0,B.jsx)(i.Z,{item:!0,children:(0,B.jsx)(S.Z,{area:t,href:n,children:"Learn more"})})})})},G=(0,o.Z)({root:{},listItem:{}});function O(e){var t=e.topics,n=G();return t?(0,B.jsx)("ul",{className:n.root,children:t.map((function(e){return(0,B.jsx)("li",{className:n.listItem,children:(0,B.jsx)(S.Z,{area:y.Z.ForumTopics,href:"https://forum.katalon.com/t/".concat(e.slug),asLink:!1,children:e.fancy_title||e.title})},e.id)}))}):null}var L=function(e){var t=e.elevation,n=r.useState(),a=(0,d.Z)(n,2),i=a[0],c=a[1],o=r.useState("https://forum.katalon.com/top/monthly?page=1&per_page=50"),u=(0,d.Z)(o,2),l=u[0],Z=u[1],m=A(),g=function(e){return e.topic_list.topics.filter((function(e){return e.tags.includes("start-page")}))},j=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,w.Z.fetchContent(k.ForumTopics).then((function(e){var t=g(e);c(t);var n="".concat(V.Z.ForumPage).concat(e.topic_list.more_topics_url);Z(n)}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return r.useEffect((function(){setTimeout((function(){j()}),1e3)}),[]),(0,B.jsxs)(f.Z,{elevation:t,className:m.card,children:[(0,B.jsx)(D,{icon:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAaCAYAAACpSkzOAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAGqADAAQAAAABAAAAGgAAAABMybYKAAABUklEQVRIDWNkgIL///8zVhx0Tvn3/38yI8N/7f//GXhgcqTQjIwMX/4zMF5lYmSc22G/dw4jI+N/kH5GEFF1yF3y959fSxgY/juB+NQDjPtYWdhi2ux2PmcC+YQ2loCc+98JZDbIDsbyA06p//79m0U9X2CaxMTElMYCihNkKXUhM4Zg9RIGAQ5RZGGGsv2OYH6X434UcXTOhx+vGdbe7GG4+e4UXApkBxMo4uEiQAY2S5DlCbFBDgSZgQxAdgDjCDV1ofsEWQOxbHQzQHYwEauZUnWjFpEdgnQLOhZSnQjLT6TqG7w+IlQywHyK7nO6+WjUIlgUkEzTL+hAdTyy80D1CaUA3QyQHUyghgSywaBKC10hsjwhNqziQ1YHsoNuVTkjqOFQtt9pD/VbQDA/Me7rctznAmx+Mf4HNYmALa99MCnq0ZDmFsgOcLsOZDCtG5AAgbCMI90RznEAAAAASUVORK5CYII=",title:"HOT DISCUSSIONS"}),(0,B.jsxs)(x.Z,{className:m.cardContent,children:[(0,B.jsx)(s.ZP,{autoHide:!0,hideTracksWhenNotNeeded:!0,height:"100%",className:m.contentWrapper,children:i?(0,B.jsx)(O,{topics:i}):(0,B.jsx)(I,{})}),(0,B.jsx)("div",{className:m.contentWrapperNoScroll,children:i?(0,B.jsx)(O,{topics:i}):(0,B.jsx)(I,{})})]}),(0,B.jsx)(M,{area:y.Z.ForumTopics,href:l})]})},q=function(e){var t=e.elevation,n=A(),a=r.useState(),i=(0,d.Z)(a,2),c=i[0],o=i[1],u=r.useState(),l=(0,d.Z)(u,2),Z=l[0],m=l[1],g=function(){var e=(0,p.Z)((0,h.Z)().mark((function e(){return(0,h.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,w.Z.fetchContent(k.News).then((function(e){o(e),m(W(e))}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return r.useEffect((function(){setTimeout((function(){g()}),1e3)}),[]),(0,B.jsxs)(f.Z,{elevation:t,className:n.card,children:[(0,B.jsx)(D,{icon:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAGKADAAQAAAABAAAAGAAAAADiNXWtAAABrElEQVRIDbVWu0oDURCduWHBFP5AsBBs/AhBUliIoNjb+ANCmm2Dbb5BsLGXCMFCMAh+hE3AQvIDFgphM86Z7IZrNo/ZRLfJvTfnnJk7O49lWvKk/eMdpq8zIToh4T1iaRhceKjrARP1hOrdTvPxY5GMYspP+nrUoNHoWoQviaRWRsQnnDHLLSVJu3PwNIz/wbpkIH1pntJY7kRoexa8bM9MnxT4onPYf4hxId6kz80ryui+qjg0jKNc04hEpzcwzyFO8stohHUtmXhMNTovbmIG8pi/reP5PKsWriTZxzuZeGsvtFrM5wkXZ+aoamLPSEWR7/fV2VLQvb/Irq3dgDz/e3E4ITVoBysir1MVcdAOVqEViW64Vn+Ylr+bVQGorWWjnPeYQohK/cNDdGFUGyEauMDrgFRb05R663A9HGhrmta7Wm+Zh1ANwxm0A4aF9fNq7JVoaEJ7kkU6LKxBraT5AHmzawNtBmwS6bCwVuvTWIgyDQyefLpN6yDv361NjOTcVjEL4IXNg9idfx2ZMGTWdVhoHG982YW2rFgMmJl5DL3SDXBYPH/x2fIDF46p9RPqMW4AAAAASUVORK5CYII=",title:"NEWS"}),(0,B.jsxs)(x.Z,{className:n.cardContent,children:[(0,B.jsx)(s.ZP,{autoHide:!0,hideTracksWhenNotNeeded:!0,height:"100%",className:n.contentWrapper,children:(0,B.jsx)(R,{area:y.Z.News,content:c})}),(0,B.jsx)("div",{className:n.contentWrapperNoScroll,children:(0,B.jsx)(R,{area:y.Z.News,content:c})})]}),(0,B.jsx)(M,{area:y.Z.News,href:Z})]})};function z(){var e=A();return(0,B.jsx)(s.$B,{autoHide:!0,hideTracksWhenNotNeeded:!0,style:{width:"100%"},autoHeightMin:"100%",autoHeightMax:"100%",children:(0,B.jsxs)(i.Z,{container:!0,spacing:2,className:e.root,children:[(0,B.jsx)(i.Z,{item:!0,md:!0,xs:12,children:(0,B.jsx)(K,{elevation:0})}),(0,B.jsxs)(i.Z,{item:!0,md:!0,xs:12,children:[(0,B.jsx)(i.Z,{item:!0,md:!0,className:e.halfCardContainer,children:(0,B.jsx)(F,{elevation:0})}),(0,B.jsx)(i.Z,{item:!0,md:!0,className:e.halfCardContainer,children:(0,B.jsx)(L,{elevation:0})})]}),(0,B.jsx)(i.Z,{item:!0,md:!0,xs:12,children:(0,B.jsx)(q,{elevation:0})})]})})}var J=n(9745);function X(){return(0,B.jsx)(a.Z,{Header:J.q,Content:z})}},996:function(e,t,n){n.d(t,{Z:function(){return l}});var r=n(4165),a=n(9439),i=n(5861),s=n(5671),c=n(3144),o=n(2388),A={CheckForUpdate:"CHECK_FOR_UPDATE",ReviewBestPractice:"REVIEW_BEST_PRACTICE",StartTour:"START_TOUR"},u=n(2434),l=function(){function e(){(0,s.Z)(this,e)}return(0,c.Z)(e,null,[{key:"do",value:function(){var e=(0,i.Z)((0,r.Z)().mark((function e(t){var n,i,s,c=arguments;return(0,r.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return n=c.length>1&&void 0!==c[1]?c[1]:{},i=Object.entries(n).map((function(e){var t=(0,a.Z)(e,2),n=t[0],r=t[1];return"".concat(n,"=").concat(encodeURIComponent(r))})).join("&"),s=i?"&".concat(i):"",e.next=5,o.Z.post("".concat(u.Z.KatalonAction,"?action=").concat(t).concat(s),{});case 5:return e.abrupt("return",e.sent);case 6:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}()},{key:"checkForUpdate",value:function(){var e=(0,i.Z)((0,r.Z)().mark((function e(){return(0,r.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.do(A.CheckForUpdate);case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e,this)})));return function(){return e.apply(this,arguments)}}()},{key:"reviewBestPractice",value:function(){var e=(0,i.Z)((0,r.Z)().mark((function e(t,n){return(0,r.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.do(A.ReviewBestPractice,{url:t,review:n});case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e,this)})));return function(t,n){return e.apply(this,arguments)}}()},{key:"startTour",value:function(){var e=(0,i.Z)((0,r.Z)().mark((function e(t){return(0,r.Z)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.do(A.StartTour,{id:t});case 2:return e.abrupt("return",e.sent);case 3:case"end":return e.stop()}}),e,this)})));return function(t){return e.apply(this,arguments)}}()}]),e}()}}]);
//# sourceMappingURL=72.d9743d82.chunk.js.map