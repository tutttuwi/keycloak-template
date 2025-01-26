package me.tutttuwi.keycloak.example.constants;

import lombok.Getter;

@Getter
public enum Template {
  INPUT_EMAIL_FTL("input-email.ftl","メールアドレス入力画面"),
  INPUT_AUTH_CODE_FTL("input-auth-code.ftl","認証コード入力画面"),
  INPUT_PASSWORD_FTL("input-password.ftl","パスワード入力画面");

  // フィールド
  private final String filename;
  private final String pageName;

  Template(String filename, String pageName) {
    this.filename = filename;
    this.pageName = pageName;
  }
}
