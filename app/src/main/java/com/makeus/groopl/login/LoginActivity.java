package com.makeus.groopl.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.makeus.groopl.R;
import com.makeus.groopl.Splash.Splash3;
import com.makeus.groopl.login.interfaces.LoginActivityView;
import com.makeus.groopl.login.models.RequestUser;
import com.makeus.groopl.src.BaseActivity;

import static com.makeus.groopl.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.makeus.groopl.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private ImageView btn_custom_login;
    private SessionCallback sessionCallback = new SessionCallback();
    Session session;
    String Access_Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_custom_login = findViewById(R.id.login_btn);

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        btn_custom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public class SessionCallback implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        // 로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
        }

        // 사용자 정보 요청
        public void requestMe() {
            UserManagement.getInstance()
                    .me(new MeV2ResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                        }

                        @Override
                        public void onFailure(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                        }

                        @Override
                        public void onSuccess(MeV2Response result) {
                            //System.out.println(result.getGroupUserToken());
                            AccessToken accessToken = Session.getCurrentSession().getTokenInfo();
                            System.out.println("onsuccess : " + accessToken.getAccessToken());
                            Access_Token = accessToken.getAccessToken();

                            Log.i("KAKAO_API", "사용자 아이디: " + result.getId());

                            UserAccount kakaoAccount = result.getKakaoAccount();
                            if (kakaoAccount != null) {

                                // 이메일
                                String email = kakaoAccount.getEmail();

                                if (email != null) {
                                    Log.i("KAKAO_API", "email: " + email);

                                } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 이메일 획득 가능
                                    // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.

                                } else {
                                    // 이메일 획득 불가
                                }

                                // 프로필
                                Profile profile = kakaoAccount.getProfile();

                                if (profile != null) {
                                    Log.d("KAKAO_API", "nickname: " + profile.getNickname());
                                    Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                    Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
                                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                                    editor.putString("nickname",profile.getNickname());
                                    editor.commit();

                                } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 프로필 정보 획득 가능

                                } else {
                                    // 프로필 획득 불가
                                }
                                loginStart(accessToken.getAccessToken());
                            }
                        }
                    });
        }
    }

    public void loginStart(String at) {
        System.out.println("login start = " + at);
        LoginService loginService = new LoginService(this);
        RequestUser requestUser = new RequestUser();
        requestUser.setAccess_token(at);
        loginService.postUser(requestUser);
    }

    @Override
    public void validateUserSuccess(String jwt, boolean isSuccess, int code, String message) {
        System.out.println("JWT = " + jwt);
        if (isSuccess) {
            showCustomToast(message);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, jwt);
            editor.commit();
            next();
        } else showCustomToast(message);
    }

    @Override
    public void validateUserFail(String msg) {
        showCustomToast(msg);
    }

    void next() {
        Intent intent = new Intent(this, Splash3.class);
        startActivity(intent);
        finish();
    }
}