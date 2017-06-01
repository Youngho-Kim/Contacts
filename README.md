# Contacts
1. Content Provider 중 Content Resovler 사용
2. Permission Check하기
[전체소스보기]



## Content Provider
    // 일종의 Database(다른 앱에서 만든 Database) 관리툴
    // ContentResolver가 Content Provider에게 필요한 데이터를 요청하고 받는 역할
    ContentResolver resolver = getContentResolver();
    
```
// ContentResolver 사용------------------------------------------------------------
     Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;  
     
     // 2. 데이터에서 가져올 컴럼명을 정의
     String projections[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID};  
                             
    //3. Content Resolver로 쿼리를 날려서 데이터를 가져온다.   
    Cursor cursor = resolver.query(phoneUri, projections, null, null ,null);    
    
    //4. 반복문을 통해 cursor에 담겨있는 데이터를 하나씩 추출
       while(cursor.moveToNext() ){        // moveToNext : 커서가 내가 가져갈 데이터를 표시. 내가 가져갈 데이터가 없을 때까지 반복함
       int idIndex = cursor.getColumnIndex(projections[0]);    // ContactsContract.CommonDataKinds.Phone.CONTACT_ID의 인덱스 번호를 가리킴
       int id = cursor.getInt(idIndex);   // // 위의 인덱스 번호를 바탕으로 커서에 있는 데이터를 가져옴  
       
    // 5. 내가 설계한 데이터 클래스에 담아준다.
        Data data = new Data();
        data.setId(id);  
        
    // 6. 여러개의 객체를 담을 수 있는 저장소에 적재한다.
     datas.add(data);
//-----------------------------------------------------------------------------------
 ```       
         
         
## CheckPermission
```java
// 1. 권한체크 - 특정 권한이 있는지 시스템에 물어본다.
if(checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
   run();
}
else {
   // 2. 권한이 없으면 사용자에 권한을 달라고 요청
    String pemission[] = {Manifest.permission.READ_CONTACTS};
    requestPermissions(pemission, REQ_PERMISSION); // -> 권한을 요구하는 팝업이 사용자 화면에 노출된다.
}
// 3. 사용자가 권한체크 팝업에서 권한을 승인 또는 거절하면 아래 메서드가 호출된다.
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
   super.onRequestPermissionsResult(requestCode, permissions, grantResults);
   if(requestCode == REQ_PERMISSION){  
      // 사용자가 권한 승인
   else{
      //  사용자가 권한 승인 거절
        }
    }
}  
```    

