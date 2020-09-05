function addDate(type,NumDay,dtDate)
{ 
   var date = new Date(dtDate); 
   type = parseInt(type) ;//类型  
   lIntval = parseInt(NumDay);//间隔 
   switch(type){ 
       case 7 ://年 
       date.setYear(date.getYear() + lIntval); 
       break;
       case 6 ://季度 
       date.setMonth(date.getMonth() + (lIntval * 3) ); 
       break;
       case 5 ://月 
       date.setMonth(date.getMonth() + lIntval) ;
       break;
       case 4 ://天 
       date.setDate(date.getDate() + lIntval) ;
       break;
       case 3 ://时 
       date.setHours(date.getHours() + lIntval); 
       break;
       case 2 ://分 
       date.setMinutes(date.getMinutes() + lIntval) ;
       break;
       case 1 ://秒 
       date.setSeconds(date.getSeconds() + lIntval) ;
       break;
       default: 
           
   } 
    //var d=date.getYear() +'-' +  (date.getMonth()+1) + '-' +date.getDate()+ ' '+    date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
    return date;
}