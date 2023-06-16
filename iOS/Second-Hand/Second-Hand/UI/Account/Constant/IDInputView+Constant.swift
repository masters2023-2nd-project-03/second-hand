//
//  IDInputView+Constant.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/16.
//

import Foundation

extension IDInputView {
    enum Constant { }
}

extension IDInputView.Constant {
    enum StringLiteral { }
    enum Layout { }
}

extension IDInputView.Constant.StringLiteral {
    static let idLabel = "아이디"
    static let placeholder = "아이디를 입력하세요"
}

extension IDInputView.Constant.Layout {
    enum IDLabel {
        static let labelLeadingPadding: CGFloat = 16
        static let labelWidth: CGFloat = 47
    }
    
    enum IDTextField {
        static let textFieldLeadingPadding: CGFloat = 52
        static let textFieldTrailingPadding: CGFloat = -16
    }
}
