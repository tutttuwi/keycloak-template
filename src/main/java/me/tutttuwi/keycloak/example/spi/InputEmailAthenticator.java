package me.tutttuwi.keycloak.example.spi;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import me.tutttuwi.keycloak.example.constants.Session;
import me.tutttuwi.keycloak.example.constants.Template;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.sessions.AuthenticationSessionModel;

@Slf4j
public class InputEmailAthenticator implements Authenticator {

  public static final String PROVIDER_ID = "input-email-authenticator";
  public static final String HELP_TEXT = "メールアドレス入力画面用プロバイダー";

  @Override
  public void authenticate(AuthenticationFlowContext context) {
    // 初期画面表示
    final Response challenge = context.form().createForm(Template.INPUT_EMAIL_FTL.getFilename());
    context.challenge(challenge);
  }

  @Override
  public void action(AuthenticationFlowContext context) {
    // セッション情報取得
    final KeycloakSession session = context.getSession();
    final AuthenticationSessionModel authSession = context.getAuthenticationSession();

    // フォーム情報取得
    final MultivaluedMap<String, String> formData =
        context.getHttpRequest().getDecodedFormParameters();
    final String email = formData.getFirst(Session.EMAIL.getKey());
    final String confirmEmail = formData.getFirst(Session.CONFIRM_EMAIL.getKey());

    // 認証情報設定
    authSession.setAuthNote(Session.EMAIL.getKey(), email);

    // メールアドレス一致チェック
    if (Objects.nonNull(email) && !email.equals(confirmEmail)) {
      final Response challenge = context.form()
          .setError("メールアドレスが一致していません。")
          .createForm(Template.INPUT_EMAIL_FTL.getFilename());
      context.challenge(challenge);
      return;
    }

    // メールアドレス重複チェック
    if (Objects.nonNull(email) && !email.isEmpty()) {
      final UserModel existingUser = session.users().getUserByEmail(context.getRealm(), email);
      if (Objects.nonNull(existingUser) && existingUser.isEmailVerified() &&
          existingUser.isEmailVerified()) {
        log.info("{}: 既に同じメールアドレスが登録されています", this.getClass());
        final Response challenge = context.form()
            .setError("既に同じメールアドレスが登録されています。")
            .createForm(Template.INPUT_EMAIL_FTL.getFilename());
        context.challenge(challenge);
        return;
      }
    }

    // 認証ユーザ仮作成
    final UserModel existingUser = session.users().getUserByEmail(context.getRealm(), email);
    final UserModel user = Objects.nonNull(existingUser) ? existingUser :
        session.users().addUser(context.getRealm(), email);
    user.setEmail(email);

    // 次のフローへ繊維
    context.success();
  }

  @Override
  public boolean requiresUser() {
    return false;
  }

  @Override
  public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel,
                               UserModel userModel) {
    return false;
  }

  @Override
  public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel,
                                 UserModel userModel) {
  }

  @Override
  public void close() {

  }
}
