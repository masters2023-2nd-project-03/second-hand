//
//  SignInViewController+Constant.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/27.
//

import UIKit

extension SignInViewController {
    enum Constant { }
}

extension SignInViewController.Constant {
    enum StringLiteral { }
    enum Layout { }
}

extension SignInViewController.Constant.StringLiteral {
    static let title = "내 계정"
}

extension SignInViewController.Constant.Layout {
    enum IdInputView {
        static let idInputViewTopInset: CGFloat = 80
        static let height: CGFloat = 44
    }
    
    enum SignInButton {
        static let signInButtonTopInset: CGFloat = 374
        static let signInButtonleadingInset: CGFloat = 16
        static let signInButtonTrailingInset: CGFloat = -16
    }
}
