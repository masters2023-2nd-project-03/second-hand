//
//  ImageSelectButton.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/14.
//

import UIKit

final class ImageSelectButton: UIButton {
    private let length: CGFloat = 80
    private let cameraImage = UIImage(systemName: "camera")
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupImageSelectButtonLayer()
        self.setupButtonLayoutConstraint()
        self.setupImageSelectButtonConfiguration()
        self.setupImageSelectButtonContent()
        self.setupImageSelectButtonAppearance()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupImageSelectButtonLayer()
        self.setupButtonLayoutConstraint()
        self.setupImageSelectButtonConfiguration()
        self.setupImageSelectButtonContent()
        self.setupImageSelectButtonAppearance()
    }
}

extension ImageSelectButton {
    private func setupImageSelectButtonLayer() {
        self.layer.borderWidth = 1
        self.layer.borderColor = ColorPalette.gray500?.cgColor
        self.layer.cornerRadius = length / 2
        self.clipsToBounds = true
    }
    
    private func setupImageSelectButtonAppearance() {
        self.tintColor = ColorPalette.black
    }
    
    private func setupImageSelectButtonContent() {
        self.setImage(cameraImage, for: .normal)
    }
    
    private func setupImageSelectButtonConfiguration() {
        let horizontalPadding = 22.5
        let verticalPadding = 25.5
        
        self.configuration = .plain()
        self.configuration?.contentInsets = NSDirectionalEdgeInsets(
            top: verticalPadding,
            leading: horizontalPadding,
            bottom: verticalPadding,
            trailing: horizontalPadding
        )
    }
    
    private func setupButtonLayoutConstraint() {
        NSLayoutConstraint.activate(
            [
                self.widthAnchor.constraint(equalToConstant: self.length),
                self.heightAnchor.constraint(equalToConstant: self.length)
            ]
        )
    }
}
