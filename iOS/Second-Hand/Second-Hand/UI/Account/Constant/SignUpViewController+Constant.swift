//
//  SignUpViewController+Constant.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/16.
//

import Foundation

extension SignUpViewController {
    enum Constant { }
}

extension SignUpViewController.Constant {
    enum StringLiteral { }
    enum Layout { }
}

extension SignUpViewController.Constant.StringLiteral {
    enum NavigationItem {
        static let closeButton = "닫기"
        static let completionButton = "완료"
        static let title = "회원가입"
    }
}

extension SignUpViewController.Constant.Layout {
    enum ImageSelectButton {
        static let imageButtonTopPadding: CGFloat = 80
    }
    
    enum IDInputView {
        static let idInputViewHeight: CGFloat = 44
        static let idInputViewTopPadding: CGFloat = 24
    }
    
    enum LocationAddButton {
        static let locationAddButtonTopPadding: CGFloat = 51
        static let locationAddButtonleadingPadding: CGFloat = 16
        static let locationAddButtonTrailingPadding: CGFloat = -16
    }
}
