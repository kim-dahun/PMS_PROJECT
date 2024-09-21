package com.prod.pms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class ObjectUtils {

    public static Map convertToMap(Object object){
        ObjectMapper objectMapper = getObjectMapper();
        try {
            return objectMapper.convertValue(object, Map.class);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    public static <T> T convertToEntity(Map<String, Object> paramMap, Class<T> className){
        ObjectMapper objectMapper = getObjectMapper();
        try {
            return objectMapper.convertValue(paramMap, className);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    public static List<Map<String,Object>> getOrderDataMap(List<Map<String, Object>> paramMaps, Set<String> orderColumns){
        paramMaps.sort((x1,x2)->{
            for(String orderKey : orderColumns){
                if(x1.get(orderKey).toString().compareTo(x2.get(orderKey).toString()) != 0){
                    return x1.get(orderKey).toString().compareTo(x2.get(orderKey).toString());
                }
            }
            return 0;
        });

        return paramMaps;
    }

    public static List<Map<String, Object>> getPivotDataMap(List<Map<String, Object>> paramMaps, String pivotColumnName, String valueColumnName){

        // PIVOT 로직이란?
        // 세로로 쭉 나열된 열 컬럼 중 하나를 가로로 나열하여 일자별, 사원별 등 다양한 조건으로 가독성있게 보는 방법
        // 액셀의 행열 피벗 테이블을 생각하면 편함
        // 비즈니스 로직을 유틸화하는 이유 : 피벗 로직은 모든 화면에서 동일한 구조로 진행되며, 파라미터만 변경되는 데, 이때 파라미터를 맵으로 관리하면
        // 피벗 로직을 만드는 데 조금 더 유연하게 코드를 작성할 수 있다.

        // 피벗 결과를 담아갈 리스트
        // Insert 작업이 많기 때문에 LinkedList를 사용
        List<Map<String, Object>> resultMapList = new LinkedList<>();

        for(Map<String, Object> paramMap : paramMaps){

            // key - value 에서 key 변경될 예정이기 때문에 새로운 맵 객체 생성

            // 피벗 컬럼의 키 값은 pivotColumnkey, value는 valueColumn 두개를 연결하고 나머지는 소계값을 제공할 수 있는
            // 환경을 구축해야 함.
            if(!paramMap.containsKey(pivotColumnName) || paramMap.get(pivotColumnName)==null){
                continue;
            }
            String columnName = paramMap.get(pivotColumnName).toString();
            String value = paramMap.containsKey(valueColumnName) ? paramMap.get(valueColumnName).toString() : null;

            Map<String, Object> refineMap = new HashMap<>(paramMap);
            refineMap.remove(pivotColumnName);
            refineMap.remove(valueColumnName);

//            for(Map.Entry<String, Object> entry : paramMap.entrySet()){
//                if(!columnName.equals(entry.getKey()) && !valueColumnName.equals(entry.getKey())){
//                    refineMap.put(entry.getKey(), entry.getValue());
//                }
//            }
            refineMap.put(columnName, value);

            boolean isNewPivotMap = true;
            for(int i = 0; i<resultMapList.size(); i++){ // 기존 리스트에서 동일한 행에 위치할 Map 찾기
                boolean isEqaulPivotMap = true;
                Map<String,Object> resultMap = resultMapList.get(i);
                for(String resultMapKey : resultMap.keySet()){
                    if(!columnName.equals(resultMapKey) && !resultMap.get(resultMapKey).equals(refineMap.get(resultMapKey))){
                        isEqaulPivotMap = false;
                    }
                }
                if(isEqaulPivotMap){ // 같은 행에 사용될 Map을 찾았으니 반복문 멈추고 병합
                    resultMap.putAll(refineMap);
                    resultMapList.set(i,resultMap);
                    isNewPivotMap = false;
                    break;
                }
            }

            if(isNewPivotMap){ // 새로운 PivotMap이므로 리스트에 추가
                resultMapList.add(refineMap);
            }

        }
        return resultMapList;
    }

    public static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}
