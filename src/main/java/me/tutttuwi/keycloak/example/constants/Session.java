package me.tutttuwi.keycloak.example.constants;

import lombok.Getter;

@Getter
public enum Session {
  EMAIL("email","メールアドレス"),
  CONFIRM_EMAIL("confirm-email","メールアドレス（確認用）"),
  AUTH_CODE("auth-code","認証コード");

  // フィールド
  private final String key;
  private final String description;

  Session(String key, String description) {
    this.key = key;
    this.description = description;
  }
}
