//
//  ImageSelectButton.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/14.
//

import UIKit

final class ImageSelectButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupImageSelectButton()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupImageSelectButton()
    }
}

extension ImageSelectButton {
    private func setupImageSelectButtonLayer() {
        self.layer.borderWidth = Constant.Layout.borderWidth
        self.layer.borderColor = ColorPalette.gray500?.cgColor
        self.layer.cornerRadius = Constant.Layout.diameter / 2
        self.clipsToBounds = true
    }
    
    private func setupImageSelectButtonAppearance() {
        self.tintColor = ColorPalette.black
    }
    
    private func setupImageSelectButtonContent() {
        self.setImage(Constant.ImageAsset.camera, for: .normal)
    }
    
    private func setupImageSelectButtonConfiguration() {
        self.configuration = .plain()
        self.configuration?.contentInsets = NSDirectionalEdgeInsets(
            top: Constant.Layout.verticalPadding,
            leading: Constant.Layout.horizontalPadding,
            bottom: Constant.Layout.verticalPadding,
            trailing: Constant.Layout.horizontalPadding
        )
    }
    
    private func setupImageSelectButton() {
        self.setupImageSelectButtonLayer()
        self.setupButtonLayoutConstraint()
        self.setupImageSelectButtonConfiguration()
        self.setupImageSelectButtonContent()
        self.setupImageSelectButtonAppearance()
    }
    
    private func setupButtonLayoutConstraint() {
        NSLayoutConstraint.activate(
            [
                self.widthAnchor.constraint(equalToConstant: Constant.Layout.diameter),
                self.heightAnchor.constraint(equalToConstant: Constant.Layout.diameter)
            ]
        )
    }
}
