//package me.tutttuwi.keycloak.example.log;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//
/// **
// * Aspectsログ設定<br/>
// * ログ出力が必要な箇所に差し込むログ設定を管理します。
// */
//@Aspect
//@Slf4j
//public class AspectLog {
//
//  @Before("within(me.tutttuwi.keycloak.example.spi.*)")
//  public void printControllerBefore(JoinPoint joinPoint) throws Exception {
//
//    log.info("[{}] {} - {}",
//        "START",
//        joinPoint.getSignature().getDeclaringType().getSimpleName(),
//        ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
//  }
//
//  @After("within(me.tutttuwi.keycloak.example.spi.*)")
//  public void printControllerAfter(JoinPoint joinPoint) throws Exception {
//    log.info("[{}] {} - {}",
//        "END",
//        joinPoint.getSignature().getDeclaringType().getSimpleName(),
//        ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
//  }
//
//  @Before("within(me.tutttuwi.keycloak.example.rest.*)")
//  public void printServiceBefore(JoinPoint joinPoint) throws Exception {
//    log.info("[{}] {} - {}",
//        "START",
//        joinPoint.getSignature().getDeclaringType().getSimpleName(),
//        ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
//  }
//
//  @After("within(me.tutttuwi.keycloak.example.rest.*)")
//  public void printServiceAfter(JoinPoint joinPoint) throws Exception {
//    log.info("[{}] {} - {}",
//        "END",
//        joinPoint.getSignature().getDeclaringType().getSimpleName(),
//        ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
//  }
//
//}