package org.example.auctionflowbackend.service;

import org.example.auctionflowbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oauth2User.getAttributes();

        // 카카오 사용자 정보에서 필요한 데이터 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        Long kakaoId = Long.parseLong(attributes.get("id").toString());
        String nickname = profile.get("nickname").toString();
        String email = kakaoAccount.get("email").toString();
        String profileImageUrl = profile.get("profile_image_url").toString();

        String accessToken = userRequest.getAccessToken().getTokenValue();
        String refreshToken = userRequest.getAdditionalParameters().get("refresh_token").toString();


        System.out.println("Attributes: " + attributes);
        System.out.println("Kakao Account: " + kakaoAccount);
        System.out.println("Profile: " + profile);
        System.out.println("Kakao ID: " + kakaoId);
        System.out.println("Nickname: " + nickname);
        System.out.println("Email: " + email);
        System.out.println("Profile Image URL: " + profileImageUrl);
        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);
        // 사용자 객체 생성 및 토큰 정보 저장
        User user = new User(email, kakaoId, nickname, profileImageUrl, accessToken, refreshToken, null);
        userService.saveOrUpdate(user);

        // 새로운 OAuth2User 객체 반환
        return new DefaultOAuth2User(
                oauth2User.getAuthorities(),
                Map.of(
                        "kakaoId", kakaoId,
                        "nickname", nickname,
                        "email", email,
                        "profileImageUrl", profileImageUrl
                ),
                "email"
        );
    }
}